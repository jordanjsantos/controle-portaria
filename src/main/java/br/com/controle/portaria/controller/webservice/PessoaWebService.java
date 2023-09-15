package br.com.controle.portaria.controller.webservice;

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

import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.services.PessoaServiceImpl;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@RestController
public class PessoaWebService implements WebServiceInterface<Pessoa>{

	private static ServiceInterfaceAbstract<Pessoa> pessoaService;

    private static synchronized ServiceInterfaceAbstract<Pessoa> getInstance() {
        if (pessoaService == null) {
        	pessoaService = new PessoaServiceImpl();
        }
        return pessoaService;
    }
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/listarPessoaRest")
	public List<Pessoa> listar() {
		pessoaService = getInstance();
		List<Pessoa> listaTipoPessoa = pessoaService.listar();
		return listaTipoPessoa;
	}
	
	@Override
	@GetMapping("/carregarPessoaRest/{id}")
	public Pessoa carregar(@PathVariable Integer id) {
		pessoaService = getInstance();
		Pessoa tipoPessoa = pessoaService.carregar(id);
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
		pessoaService = getInstance();
		pessoaService.salvar(tipoPessoa);
	}

	@Override
	@DeleteMapping("/excluirPessoaRest/{id}")
	public void excluir(@PathVariable Integer id) {
		pessoaService = getInstance();
		pessoaService.excluir((Integer[]) Arrays.asList(id).toArray());
	}

}
