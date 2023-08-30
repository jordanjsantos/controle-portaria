package br.com.controle.portaria.services;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public interface ServiceInterface <T> {
	
	public List<T> listar();
	
	public T carregar(Integer id);
	
	public void salvar(T entity);		
	
	public void excluir(Integer[] ids) ;

}
