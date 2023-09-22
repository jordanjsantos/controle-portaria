package br.com.controle.portaria.controller.web.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.controle.portaria.controller.web.WebControllerInterface;
import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.ControleRegistro;
import br.com.controle.portaria.model.Usuario;

@Controller
public class RegistroControllerImpl implements WebControllerInterface<ControleRegistro>{
	
	private static GenericDao<ControleRegistro> dao;

    private static synchronized GenericDao<ControleRegistro> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<ControleRegistro>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/controleRegistro")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		GenericDao<ControleRegistro> dao = getInstance();		
		List<ControleRegistro> listaControleRegistro = new ArrayList<ControleRegistro>();
		listaControleRegistro = dao.listaTudo("from ControleRegistro");
		
		model.addAttribute("listControleRegistro", listaControleRegistro);
		
		//model.addAttribute("listUsuario", new UsuarioControllerImpl().getListaUsuario());
		model.addAttribute("listPessoa", new PessoaControllerImpl().getListaPessoa());
		//model.addAttribute("listImovelCond", new ImovelCondominioControllerImpl().getListaImovelCondominio());
				
		return "cadastroControleRegistroForm";
	}
	

	@Override
	@RequestMapping(value = "/carregarControleRegistro", method = RequestMethod.GET)
	public String carregar(@RequestParam("idControleRegistro")
										Integer idControleRegistro, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<ControleRegistro> dao = getInstance();	
		ControleRegistro controleRegistro = new ControleRegistro();
		
		if(idControleRegistro != null){
			controleRegistro = dao.carrega(idControleRegistro, ControleRegistro.class);
			model.addAttribute("controleRegistro", controleRegistro);
		}		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarControleRegistro", method = RequestMethod.POST)
	public String salvar(ControleRegistro controleRegistro, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<ControleRegistro> dao = getInstance();	
		
		Usuario userTemp = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuarioFinal = new UsuarioControllerImpl().obterUsuarioPorLogin(userTemp.getUser());
		
		controleRegistro.setDataHoraReg(
				Date.from(LocalDateTime.now()
					      .atZone(ZoneId.systemDefault())
					      .toInstant()));			
				
		controleRegistro.setUsuario(usuarioFinal);
		
		dao.adicionarOrAlterar(controleRegistro);
		model.addAttribute("controleRegistro", controleRegistro);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirControleRegistro", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<ControleRegistro> dao = getInstance();	
		Collection<ControleRegistro> listaControleRegistro = new ArrayList<ControleRegistro>();	
		
		for(Integer i : cds ){			
			ControleRegistro controleRegistro = new ControleRegistro();			
			if(i != null){
				dao = getInstance();	
				controleRegistro = dao.carrega(i, ControleRegistro.class);				
			}				
			if(!controleRegistro.equals(null)){
				listaControleRegistro.add(controleRegistro);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaControleRegistro);
		
		return listar(model);
	}
}
