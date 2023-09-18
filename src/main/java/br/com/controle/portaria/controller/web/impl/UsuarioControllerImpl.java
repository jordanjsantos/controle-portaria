package br.com.controle.portaria.controller.web.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.controle.portaria.controller.web.WebControllerInterface;
import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.ServiceInterface;
import br.com.controle.portaria.services.impl.PessoaServiceImpl;
import br.com.controle.portaria.services.impl.UsuarioServiceImpl;

@Controller
public class UsuarioControllerImpl implements WebControllerInterface<Usuario>{
	
	private static UsuarioServiceImpl service;
	private static ServiceInterface<Pessoa> servicePessoa;
	
    private static synchronized UsuarioServiceImpl getInstance() {
        return service == null ? new UsuarioServiceImpl() : service;
    }
    
    private static synchronized ServiceInterface<Pessoa> getInstancePessoa() {
        return servicePessoa == null ? new PessoaServiceImpl() : servicePessoa;
    }
    
    

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/usuario")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		model.addAttribute("listUsuario", getInstance().listar());
		
		model.addAttribute("listPessoa", getInstancePessoa().listar());
				
		return "cadastroUsuarioForm";
	}
	
	public Usuario obterUsuarioPorLogin(String login) {
		Usuario usuario = getInstance().obterUsuarioPorLogin(login);
		return usuario;
	}

	@Override
	@RequestMapping(value = "/carregarUsuario", method = RequestMethod.GET)
	public String carregar(@RequestParam("idUsuario")Integer idUsuario, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		Usuario usuario = getInstance().carregar(idUsuario);
		model.addAttribute("usuario", usuario);
		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public String salvar(Usuario usuario, BindingResult result, Model model, HttpSession session) {
		System.out.println(this.getClass().getName() + "#############salvar#########");	
		getInstance().salvar(usuario);
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
		getInstance().excluir(cds);
		
		return listar(model);
	}
}
