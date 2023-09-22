package br.com.controle.portaria.controller.webservice.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.portaria.controller.webservice.WebServiceInterface;
import br.com.controle.portaria.model.ImovelCondominio;
import br.com.controle.portaria.services.ServiceInterface;
import br.com.controle.portaria.services.impl.ImovelCondominioServiceImpl;

@RestController
public class ImovelCondominioWebServiceImpl implements WebServiceInterface<ImovelCondominio> {

	private static ServiceInterface<ImovelCondominio> service;

    private static synchronized ServiceInterface<ImovelCondominio> getInstance() {
        if (service == null) {
        	service = new ImovelCondominioServiceImpl();
        }
        return service;
    }
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarImovelCondominioRest")
	public List<ImovelCondominio> listar() {
		service = getInstance();
		List<ImovelCondominio> lista = service.listar();
		return lista;
	}

	@Override
	@GetMapping("/carregarImovelCondominioRest/{id}")
	public ImovelCondominio carregar(Integer id) {
		service = getInstance();
		ImovelCondominio imovel = service.carregar(id);
		return imovel;
	}

	@Override
	@PutMapping("/salvarImovelCondominioRest")
	public void salvar(ImovelCondominio imovel) {
		service = getInstance();
		service.salvar(imovel);
	}

	@Override
	@DeleteMapping("/excluirImovelCondominioRest/{id}")
	public void excluir(Integer id) {
		service = getInstance();
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}
}
