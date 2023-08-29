package br.com.controle.portaria.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;

@Controller
public class LoginControllerImpl {
	
	private static Logger LOGGER = Logger.getLogger(LoginControllerImpl.class);
		
	@RequestMapping("acessoSistema")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(@Validated Usuario usuario, HttpSession session, HttpServletRequest request) {		
		try {
			GenericDao<Usuario> dao = new GenericDao<Usuario>();		
			dao = carregaDao();	
			
			if(dao.naoExisteUsuario(usuario)) {		
				session.setAttribute("msgErrorPasword", "Usuário ou Senha inválida");
				return "redirect:acessoSistema";		    
			} else {
				session.removeAttribute("msgErrorPasword");
			}
			session.setAttribute("usuarioLogado", usuario);
		} catch (Exception e) {
			LOGGER.error("Error", e);
			session.setAttribute("msgErrorPasword", "Erro Interno, tente novamente");
			return "redirect:acessoSistema";
		}
		return "indexForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("#############log logout#############");
		session.invalidate();
		return "redirect:acessoSistema";
	}
	
	private GenericDao<Usuario> carregaDao(){
		return new GenericDao<Usuario>();
	}
}
