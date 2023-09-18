package br.com.controle.portaria.controller.webservice.impl;

import java.util.ArrayList;
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
import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;
import br.com.controle.portaria.services.impl.PessoaServiceImpl;

@RestController
public class PessoaWebServiceImpl implements WebServiceInterface<Pessoa>{

	private static ServiceInterfaceAbstract<Pessoa> service;

    private static synchronized ServiceInterfaceAbstract<Pessoa> getInstance() {
        if (service == null) {
        	service = new PessoaServiceImpl();
        }
        return service;
    }
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarPessoaRest")
	public List<Pessoa> listar() {
		service = getInstance();
		List<Pessoa> listaTipoPessoa = service.listar();
		return listaTipoPessoa;
	}
	
	@Override
	@GetMapping("/carregarPessoaRest/{id}")
	public Pessoa carregar(@PathVariable Integer id) {
		service = getInstance();
		Pessoa tipoPessoa = service.carregar(id);
		return tipoPessoa;
	}
	
	@GetMapping("/carregarPessoaRest/{nome}")
	public List<Pessoa> carregar(@PathVariable("nome")String nome) {
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		if(nome != null && !"".equals(nome)){
			listPessoa = getInstance().carregar("nome");
		}		
		return listPessoa;
	}

	@Override
	@PutMapping("/salvarPessoaRest")
	public void salvar(@RequestBody Pessoa tipoPessoa) {
		service = getInstance();
		service.salvar(tipoPessoa);
	}

	@Override
	@DeleteMapping("/excluirPessoaRest/{id}")
	public void excluir(@PathVariable Integer id) {
		service = getInstance();
		service.excluir((Integer[]) Arrays.asList(id).toArray());
	}

}
