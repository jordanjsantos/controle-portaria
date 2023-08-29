package br.com.controle.portaria.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Pessoa;

@Controller
public class PessoaControllerImpl implements WebControllerInterface<Pessoa>{
	
	private static GenericDao<Pessoa> dao;

    private static synchronized GenericDao<Pessoa> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<Pessoa>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/pessoa")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		List<Pessoa> listaPessoa = getListaPessoa();
		
		model.addAttribute("listPessoa", listaPessoa);
		
		model.addAttribute("listTipoPessoa", new TipoPessoaControllerImpl().getListaTipoPessoa());
		
		return "cadastroPessoaForm";
	}
	
	public List<Pessoa> getListaPessoa() {
		GenericDao<Pessoa> dao = getInstance();		
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		listaPessoa = dao.listaTudo("from Pessoa");
		return listaPessoa;
	}

	@Override
	@RequestMapping(value = "/carregarPessoa", method = RequestMethod.GET)
	public String carregar(@RequestParam("idPessoa")Integer idPessoa, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<Pessoa> dao = getInstance();	
		Pessoa pessoa = new Pessoa();
		
		if(idPessoa != null){
			pessoa = dao.carrega(idPessoa, Pessoa.class);
			model.addAttribute("pessoa", pessoa);
		}		
		return listar(model);
	}
	
	@RequestMapping(value = "/buscarPessoaPorNome", method = RequestMethod.POST)
	@ResponseBody
	public String carregar(@RequestParam("nome")String nome, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<Pessoa> dao = getInstance();	
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		
		if(nome != null && !"".equals(nome)){
			listPessoa = dao.busca("nome", nome, Pessoa.class);
			model.addAttribute("listPessoa", listPessoa);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				return ow.writeValueAsString(listPessoa);			
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}		
		return null;
	}

	@Override
	@RequestMapping(value = "/salvarPessoa", method = RequestMethod.POST)
	public String salvar(Pessoa pessoa, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<Pessoa> dao = getInstance();	
		dao.adicionarOrAlterar(pessoa);
		model.addAttribute("pessoa", pessoa);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirPessoa", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<Pessoa> dao = getInstance();	
		Collection<Pessoa> listaVeiculo = new ArrayList<Pessoa>();	
		
		for(Integer i : cds ){			
			Pessoa veiculo = new Pessoa();			
			if(i != null){
				dao = getInstance();	
				veiculo = dao.carrega(i, Pessoa.class);				
			}				
			if(!veiculo.equals(null)){
				listaVeiculo.add(veiculo);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaVeiculo);
		
		return listar(model);
	}
}
