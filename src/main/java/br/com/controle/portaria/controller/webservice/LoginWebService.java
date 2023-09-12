package br.com.controle.portaria.controller.webservice;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.LoginServiceImpl;

@RestController
public class LoginWebService {
	
	private static LoginServiceImpl usuarioService;
	
	private static synchronized LoginServiceImpl getInstance() {
        if (usuarioService == null) {
        	usuarioService = new LoginServiceImpl();
        }
        return usuarioService;
    }

	@PutMapping("/efetuaLoginRest")
	public ResponseEntity<Object> efetuaLogin(@RequestBody Usuario usuario, HttpSession session) {		
		try {
			LoginServiceImpl usuarioService = getInstance();
			
			if(usuarioService.naoExisteUsuario(usuario)) {		
				return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
			} 
		} catch (Exception e) {
			session.setAttribute("msgErrorPasword", "Erro Interno, tente novamente");
		}
		session.setAttribute("usuarioLogado", usuario);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("/logoutRest")
	public ResponseEntity<Object> logout(HttpSession session) {
		System.out.println("#############log logout#############");
		session.invalidate();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
