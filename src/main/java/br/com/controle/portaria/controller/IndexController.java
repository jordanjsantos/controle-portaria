package br.com.controle.portaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value="/")
	public String principal(Model model){
		System.out.println("#############log /#############");		
		return "index";
	}

}
