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
import br.com.controle.portaria.model.TipoPessoa;
import br.com.controle.portaria.services.ServiceInterface;
import br.com.controle.portaria.services.impl.TipoPessoaServiceImpl;

@RestController
public class TipoPessoaWebServiceImpl implements WebServiceInterface<TipoPessoa>{

	private static ServiceInterface<TipoPessoa> service;

    private static synchronized ServiceInterface<TipoPessoa> getInstance() {
        if (service == null) {
        	service = new TipoPessoaServiceImpl();
        }
        return service;
    }
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarTipoPessoaRest")
	public List<TipoPessoa> listar() {
		service = getInstance();
		List<TipoPessoa> listaTipoPessoa = service.listar();
		return listaTipoPessoa;
	}
	
	@Override
	@GetMapping("/carregarTipoPessoaRest/{id}")
	public TipoPessoa carregar(@PathVariable Integer id) {
		service = getInstance();
		TipoPessoa tipoPessoa = service.carregar(id);
		return tipoPessoa;
	}

	@Override
	@PutMapping("/salvarTipoPessoaRest")
	public void salvar(@RequestBody TipoPessoa tipoPessoa) {
		service = getInstance();
		service.salvar(tipoPessoa);
	}

	@Override
	@DeleteMapping("/excluirTipoPessoaRest/{id}")
	public void excluir(@PathVariable Integer id) {
		service = getInstance();
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}

}
