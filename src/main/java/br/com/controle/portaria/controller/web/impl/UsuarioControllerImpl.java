package br.com.controle.portaria.controller.web.impl;

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

import br.com.controle.portaria.controller.web.WebControllerInterface;
import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;

@Controller
public class UsuarioControllerImpl implements WebControllerInterface<Usuario>{
	
	private static GenericDao<Usuario> dao;

    private static synchronized GenericDao<Usuario> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<Usuario>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/usuario")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		model.addAttribute("listUsuario", getListaUsuario());
		
		model.addAttribute("listPessoa", new PessoaControllerImpl().getListaPessoa());
				
		return "cadastroUsuarioForm";
	}
	
	public List<Usuario> getListaUsuario() {
		GenericDao<Usuario> dao = getInstance();		
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		listaUsuario = dao.listaTudo("from Usuario");
		return listaUsuario;
	}
	
	public Usuario obterUsuarioPorLogin(String login) {
		GenericDao<Usuario> dao = getInstance();
		List<Usuario> lUsuarios = dao.busca("user", login, Usuario.class);
		if(!lUsuarios.isEmpty()) {
			return lUsuarios.get(0);
		}
		return new Usuario();
	}

	@Override
	@RequestMapping(value = "/carregarUsuario", method = RequestMethod.GET)
	public String carregar(@RequestParam("idUsuario")Integer idUsuario, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<Usuario> dao = getInstance();	
		Usuario usuario = new Usuario();
		
		if(idUsuario != null){
			usuario = dao.carrega(idUsuario, Usuario.class);
			model.addAttribute("usuario", usuario);
		}		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public String salvar(Usuario usuario, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<Usuario> dao = getInstance();	
		if(usuario != null && !"".equals(usuario.getSenha())) {
			usuario.setSenha(usuario.getSenhaCriptografadaMD5());
		}
		dao.adicionarOrAlterar(usuario);
		model.addAttribute("usuario", usuario);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirUsuario", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<Usuario> dao = getInstance();	
		Collection<Usuario> listaUsuario = new ArrayList<Usuario>();	
		
		for(Integer i : cds ){			
			Usuario usuario = new Usuario();			
			if(i != null){
				dao = getInstance();	
				usuario = dao.carrega(i, Usuario.class);				
			}				
			if(!usuario.equals(null)){
				listaUsuario.add(usuario);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaUsuario);
		
		return listar(model);
	}
}
