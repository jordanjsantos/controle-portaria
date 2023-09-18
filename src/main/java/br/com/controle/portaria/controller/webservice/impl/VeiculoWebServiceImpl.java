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
import br.com.controle.portaria.model.Veiculo;
import br.com.controle.portaria.services.ServiceInterface;
import br.com.controle.portaria.services.impl.VeiculoServiceImpl;

@RestController
public class VeiculoWebServiceImpl implements WebServiceInterface<Veiculo>{

	private static ServiceInterface<Veiculo> service;

	private static synchronized ServiceInterface<Veiculo> getInstance() {
		if (service == null) {
			service = new VeiculoServiceImpl();
		}
		return service;
	}
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarVeiculoRest")
	public List<Veiculo> listar() {
		service = getInstance();
		List<Veiculo> listaVeiculo = service.listar();
		return listaVeiculo;
	}

	@Override
	@GetMapping("/carregarVeiculoRest/{id}")
	public Veiculo carregar(@PathVariable Integer id) {
		service = getInstance();
		Veiculo veiculo = service.carregar(id);
		return veiculo;
	}

	@Override
	@PutMapping("/salvarVeiculoRest")
	public void salvar(@RequestBody Veiculo veiculo) {
		service = getInstance();
		service.salvar(veiculo);
	}

	@Override
	@DeleteMapping("/excluirVeiculoRest/{id}")
	public void excluir(@PathVariable Integer id) {
		service = getInstance();
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}

}
