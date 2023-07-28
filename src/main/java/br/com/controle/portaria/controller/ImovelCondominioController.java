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
import br.com.controle.portaria.model.ImovelCondominio;

@Controller
public class ImovelCondominioController implements InterfaceCadastroController<ImovelCondominio>{
	
	private static GenericDao<ImovelCondominio> dao;

    private static synchronized GenericDao<ImovelCondominio> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<ImovelCondominio>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/imovelCondominio")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		GenericDao<ImovelCondominio> dao = getInstance();		
		List<ImovelCondominio> listaImovelCondominio = new ArrayList<ImovelCondominio>();
		listaImovelCondominio = dao.listaTudo("from ImovelCondominio");
		
		model.addAttribute("listImovelCondominio", listaImovelCondominio);
		
		model.addAttribute("listPessoa", new PessoaController().getListaPessoa());
				
		return "cadastroImovelCondominioForm";
	}

	@Override
	@RequestMapping(value = "/carregarImovelCondominio", method = RequestMethod.GET)
	public String carregar(@RequestParam("idImovelCondominio")Integer idImovelCondominio, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<ImovelCondominio> dao = getInstance();	
		ImovelCondominio imovelCondominio = new ImovelCondominio();
		
		if(idImovelCondominio != null){
			imovelCondominio = dao.carrega(idImovelCondominio, ImovelCondominio.class);
			model.addAttribute("imovelCondominio", imovelCondominio);
		}		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarImovelCondominio", method = RequestMethod.POST)
	public String salvar(ImovelCondominio imovelCondominio, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<ImovelCondominio> dao = getInstance();	
		dao.adicionarOrAlterar(imovelCondominio);
		model.addAttribute("imovelCondominio", imovelCondominio);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirImovelCondominio", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<ImovelCondominio> dao = getInstance();	
		Collection<ImovelCondominio> listaImovelCondominio = new ArrayList<ImovelCondominio>();	
		
		for(Integer i : cds ){			
			ImovelCondominio imovelCondominio = new ImovelCondominio();			
			if(i != null){
				dao = getInstance();	
				imovelCondominio = dao.carrega(i, ImovelCondominio.class);				
			}				
			if(!imovelCondominio.equals(null)){
				listaImovelCondominio.add(imovelCondominio);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaImovelCondominio);
		
		return listar(model);
	}
}
