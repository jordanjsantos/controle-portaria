package br.com.controle.portaria.controller.webservice.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.portaria.controller.webservice.WebServiceInterface;
import br.com.controle.portaria.model.ImovelCondominio;
import br.com.controle.portaria.services.ServiceInterface;

@RestController
public class ImovelCondominioWebServiceImpl implements WebServiceInterface<ImovelCondominio> {

	@Autowired
	private ServiceInterface<ImovelCondominio> service;

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarImovelCondominioRest")
	public List<ImovelCondominio> listar() {
		List<ImovelCondominio> lista = service.listar();
		return lista;
	}

	@Override
	@GetMapping("/carregarImovelCondominioRest/{id}")
	public ImovelCondominio carregar(Integer id) {
		ImovelCondominio imovel = service.carregar(id);
		return imovel;
	}

	@Override
	@PutMapping("/salvarImovelCondominioRest")
	public void salvar(ImovelCondominio imovel) {
		service.salvar(imovel);
	}

	@Override
	@DeleteMapping("/excluirImovelCondominioRest/{id}")
	public void excluir(Integer id) {
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}
}
