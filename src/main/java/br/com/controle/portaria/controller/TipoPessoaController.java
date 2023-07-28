package br.com.controle.portaria.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.model.TipoPessoa;

@Controller
public class TipoPessoaController implements InterfaceCadastroController<TipoPessoa>{
	
	private static GenericDao<TipoPessoa> dao;

    private static synchronized GenericDao<TipoPessoa> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<TipoPessoa>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/tipoPessoa")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		List<TipoPessoa> listaTipoPessoa = getListaTipoPessoa();
		
		model.addAttribute("listTipoPessoa", listaTipoPessoa);		
				
		return "cadastroTipoPessoaForm";
	}
	
	public List<TipoPessoa> getListaTipoPessoa() {
		GenericDao<TipoPessoa> dao = getInstance();		
		List<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = dao.listaTudo("from TipoPessoa");		
		return listaTipoPessoa;
	}

	@Override
	@RequestMapping(value = "/carregarTipoPessoa", method = RequestMethod.GET)
	public String carregar(@RequestParam("idTipoPessoa")Integer idTipoPessoa, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<TipoPessoa> dao = getInstance();	
		TipoPessoa tipoPessoa = new TipoPessoa();
		
		if(idTipoPessoa != null){
			tipoPessoa = dao.carrega(idTipoPessoa, TipoPessoa.class);
			model.addAttribute("tipoPessoa", tipoPessoa);
		}		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarTipoPessoa", method = RequestMethod.POST)
	public String salvar(TipoPessoa tipoPessoa, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<TipoPessoa> dao = getInstance();	
		dao.adicionarOrAlterar(tipoPessoa);
		model.addAttribute("tipoPessoa", tipoPessoa);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirTipoPessoa", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<TipoPessoa> dao = getInstance();	
		Collection<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();	
		
		for(Integer i : cds ){			
			TipoPessoa tipoPessoa = new TipoPessoa();			
			if(i != null){
				dao = getInstance();	
				tipoPessoa = dao.carrega(i, TipoPessoa.class);				
			}				
			if(!tipoPessoa.equals(null)){
				listaTipoPessoa.add(tipoPessoa);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaTipoPessoa);
		
		return listar(model);
	}
}
