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
	
	public List<T>  listar();
	
	public T carregar(@PathVariable Integer id);
	
	public void salvar(@RequestBody T newEmployee);		
	
	public void excluir(@PathVariable Integer id);

}
