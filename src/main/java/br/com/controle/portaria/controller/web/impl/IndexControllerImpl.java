package br.com.controle.portaria.controller.web.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControllerImpl {
	
	@RequestMapping(value="/")
	public String principal(Model model){
		System.out.println("#############log /#############");		
		return "indexForm";
	}

}
