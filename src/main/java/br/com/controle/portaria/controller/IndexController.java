package br.com.controle.portaria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value="/")
	public String principal(Model model){
		System.out.println("#############log /#############");	
		List<String> list = new ArrayList<String>();
		list.add("Fernando");
		list.add("Pascott");
		list.add("Tech Dev Pascott");
		list.add("Deixe o seu like!");
		
		list.forEach(s -> {
			System.out.println(s);
		});
		
		return "index";
	}

}
