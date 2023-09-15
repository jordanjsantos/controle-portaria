package br.com.controle.portaria.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.services.PessoaServiceImpl;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@Controller
public class PessoaControllerImpl implements WebControllerInterface<Pessoa>{
	
	private static ServiceInterfaceAbstract<Pessoa> pessoaService;

    private static synchronized ServiceInterfaceAbstract<Pessoa> getInstance() {
        if (pessoaService == null) {
        	pessoaService = new PessoaServiceImpl();
        }
        return pessoaService;
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
		
		return "cadastroPessoaForm";
	}
	
	public List<Pessoa> getListaPessoa() {
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		listaPessoa = getInstance().listar();;
		return listaPessoa;
	}

	@Override
	@RequestMapping(value = "/carregarPessoa", method = RequestMethod.GET)
	public String carregar(@RequestParam("idPessoa")Integer idPessoa, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		Pessoa pessoa = new Pessoa();
		
		if(idPessoa != null){
			pessoa = getInstance().carregar(idPessoa);
			model.addAttribute("pessoa", pessoa);
		}		
		return listar(model);
	}
	
	@RequestMapping(value = "/buscarPessoaPorNome", method = RequestMethod.POST)
	@ResponseBody
	public String carregar(@RequestParam("nome")String nome, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		
		if(nome != null && !"".equals(nome)){
			listPessoa = getInstance().carregar("nome");
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
		getInstance().salvar(pessoa);
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
		getInstance().excluir(cds);
		return listar(model);
	}
}
