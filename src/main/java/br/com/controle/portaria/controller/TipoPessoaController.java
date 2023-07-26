package br.com.controle.portaria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.TipoPessoa;

@Controller
public class TipoPessoaController {

	@RequestMapping(value="/tipoPessoa")
	public String principal(Model model){
		GenericDao<TipoPessoa> dao = new GenericDao<TipoPessoa>();		
		System.out.println("#############log /tipoPessoa#############");
		
		List<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = dao.listaTudo("from TipoPessoa");
		
		model.addAttribute("listTipoPessoa", listaTipoPessoa);		
				
		return "cadastroTipoPessoaForm";
	}
}
