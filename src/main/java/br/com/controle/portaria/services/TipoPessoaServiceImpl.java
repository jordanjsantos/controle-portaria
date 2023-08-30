package br.com.controle.portaria.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.TipoPessoa;

@Controller
public class TipoPessoaServiceImpl implements ServiceInterface<TipoPessoa>{
	
	//private static Logger LOGGER = Logger.getLogger(TipoPessoaServiceImpl.class);
	private static GenericDao<TipoPessoa> dao;

    private static synchronized GenericDao<TipoPessoa> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<TipoPessoa>();
        }
        return dao;
    }

	@Override
	public List<TipoPessoa> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");
		GenericDao<TipoPessoa> dao = getInstance();		
		List<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = dao.listaTudo("from TipoPessoa");		
		return listaTipoPessoa;
	}
	
	@Override
	public TipoPessoa carregar(Integer idTipoPessoa) {
		GenericDao<TipoPessoa> dao = getInstance();	
		TipoPessoa tipoPessoa = new TipoPessoa();
		
		if(idTipoPessoa != null){
			tipoPessoa = dao.carrega(idTipoPessoa, TipoPessoa.class);
		}		
		return tipoPessoa;
	}

	@Override
	public void salvar(TipoPessoa tipoPessoa) {
		GenericDao<TipoPessoa> dao = getInstance();	
		dao.adicionarOrAlterar(tipoPessoa);
	}

	@Override
	public void excluir(Integer[] ids) {
		GenericDao<TipoPessoa> dao = getInstance();	
		Collection<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();	
		
		for(Integer i : ids ){			
			TipoPessoa tipoPessoa = new TipoPessoa();			
			if(i != null){
				dao = getInstance();	
				tipoPessoa = dao.carrega(i, TipoPessoa.class);				
			}				
			if(!tipoPessoa.equals(null)){
				listaTipoPessoa.add(tipoPessoa);
			}			
		}	
		dao = getInstance();	
		dao.excluir(listaTipoPessoa);
	}
}
