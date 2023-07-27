package br.com.controle.portaria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public interface InterfaceCadastroController <T> {
	
	@InitBinder
	public void dataBinding(WebDataBinder binder);
	
	@RequestMapping(value = "/cadastroModelLista")
	public String  listar(Model model);
	
	@RequestMapping(value = "/carregarModel", method=RequestMethod.GET)
	public String carregar(@RequestParam("cdModel") Integer cdModel, Model model);
	
	@RequestMapping(value = "/salvarModel", method = RequestMethod.POST)
	public String salvar(@Validated T t, BindingResult result, Model model, HttpSession session);		
	
	@RequestMapping(value="/excluirModel", method=RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) ;
	

}
