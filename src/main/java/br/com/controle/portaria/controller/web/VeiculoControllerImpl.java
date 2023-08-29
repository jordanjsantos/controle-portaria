package br.com.controle.portaria.controller.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Veiculo;

@Controller
public class VeiculoControllerImpl implements WebControllerInterface<Veiculo>{
	
	private static GenericDao<Veiculo> dao;

    private static synchronized GenericDao<Veiculo> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<Veiculo>();
        }
        return dao;
    }

	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value="/veiculo")
	public String listar(Model model) {
		System.out.println(this.getClass().getName() + "#############listar#########");

		GenericDao<Veiculo> dao = getInstance();		
		List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
		listaVeiculo = dao.listaTudo("from Veiculo");
		
		model.addAttribute("listVeiculo", listaVeiculo);
		
		model.addAttribute("listPessoa", new PessoaControllerImpl().getListaPessoa());
				
		return "cadastroVeiculoForm";
	}

	@Override
	@RequestMapping(value = "/carregarVeiculo", method = RequestMethod.GET)
	public String carregar(@RequestParam("idVeiculo")Integer idVeiculo, Model model) {
		System.out.println(this.getClass().getName() + "#############carregar#########");
		
		GenericDao<Veiculo> dao = getInstance();	
		Veiculo veiculo = new Veiculo();
		
		if(idVeiculo != null){
			veiculo = dao.carrega(idVeiculo, Veiculo.class);
			model.addAttribute("veiculo", veiculo);
		}		
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/salvarVeiculo", method = RequestMethod.POST)
	public String salvar(Veiculo veiculo, BindingResult result, Model model, HttpSession session) {

		System.out.println(this.getClass().getName() + "#############salvar#########");	
		
		GenericDao<Veiculo> dao = getInstance();	
		dao.adicionarOrAlterar(veiculo);
		model.addAttribute("veiculo", veiculo);
		if (result.hasErrors()) {			
			return "error";
		}
		return listar(model);
	}

	@Override
	@RequestMapping(value = "/excluirVeiculo", method = RequestMethod.POST)
	public String excluir(@RequestParam("cds") Integer[] cds, Model model) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<Veiculo> dao = getInstance();	
		Collection<Veiculo> listaVeiculo = new ArrayList<Veiculo>();	
		
		for(Integer i : cds ){			
			Veiculo veiculo = new Veiculo();			
			if(i != null){
				dao = getInstance();	
				veiculo = dao.carrega(i, Veiculo.class);				
			}				
			if(!veiculo.equals(null)){
				listaVeiculo.add(veiculo);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaVeiculo);
		
		return listar(model);
	}
}
