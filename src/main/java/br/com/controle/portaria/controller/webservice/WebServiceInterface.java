package br.com.controle.portaria.controller.webservice;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public interface WebServiceInterface <T> {
	
	@InitBinder
	public void dataBinding(WebDataBinder binder);
	
	@GetMapping("/cadastroModelLista")
	public List<T>  listar();
	
	@GetMapping("/carregarModel/{id}")
	public T carregar(@PathVariable Integer id);
	
	@PutMapping("/salvarModel/{id}")
	public String salvar(@RequestBody T newEmployee, @PathVariable Long id);		
	
	@DeleteMapping("/excluirModel/{id}")
	public String excluir(@PathVariable Integer id);

}
