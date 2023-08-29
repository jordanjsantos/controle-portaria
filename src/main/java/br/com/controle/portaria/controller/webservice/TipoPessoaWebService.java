package br.com.controle.portaria.controller.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.TipoPessoa;

@RestController
public class TipoPessoaWebService implements WebServiceInterface<TipoPessoa>{

	private static GenericDao<TipoPessoa> dao;

    private static synchronized GenericDao<TipoPessoa> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<TipoPessoa>();
        }
        return dao;
    }
	
	@Override
	public void dataBinding(WebDataBinder binder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/getTipoPessoaRest")
	public List<TipoPessoa> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");
		List<TipoPessoa> listaTipoPessoa = getListaTipoPessoa();
		return listaTipoPessoa;
	}
	
	public List<TipoPessoa> getListaTipoPessoa() {
		GenericDao<TipoPessoa> dao = getInstance();		
		List<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = dao.listaTudo("from TipoPessoa");		
		return listaTipoPessoa;
	}

	@Override
	public TipoPessoa carregar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String salvar(TipoPessoa newEmployee, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
