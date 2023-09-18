package br.com.controle.portaria.controller.webservice.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.portaria.controller.webservice.WebServiceInterface;
import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.ServiceInterface;
import br.com.controle.portaria.services.impl.UsuarioServiceImpl;

@RestController
public class UsuarioWebServiceImpl implements WebServiceInterface<Usuario> {

	private static ServiceInterface<Usuario> service;

	private static synchronized ServiceInterface<Usuario> getInstance() {
		if (service == null) {
			service = new UsuarioServiceImpl();
		}
		return service;
	}

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub

	}

	@Override
	@GetMapping("/listarUsuarioRest")
	public List<Usuario> listar() {
		service = getInstance();
		List<Usuario> listaTipoPessoa = service.listar();
		return listaTipoPessoa;
	}

	@Override
	@GetMapping("/carregarUsuarioRest/{id}")
	public Usuario carregar(@PathVariable Integer id) {
		service = getInstance();
		Usuario usuario = service.carregar(id);
		return usuario;
	}

	@Override
	@PutMapping("/salvarUsuarioRest")
	public void salvar(@RequestBody Usuario usuario) {
		service = getInstance();
		service.salvar(usuario);

	}

	@Override
	@DeleteMapping("/excluirUsuarioRest/{id}")
	public void excluir(Integer id) {
		service = getInstance();
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}
}
