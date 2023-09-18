package br.com.controle.portaria.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.TipoPessoa;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;


@SuppressWarnings("unchecked")
public class TipoPessoaServiceImpl extends ServiceInterfaceAbstract<TipoPessoa> {
	
	@Override
	public List<TipoPessoa> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");
		GenericDao<TipoPessoa> dao = (GenericDao<TipoPessoa>) getInstanceDao();		
		List<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = dao.listaTudo("from TipoPessoa");		
		return listaTipoPessoa;
	}
	
	@Override
	public TipoPessoa carregar(Integer idTipoPessoa) {
		GenericDao<TipoPessoa> dao = (GenericDao<TipoPessoa>) getInstanceDao();	
		TipoPessoa tipoPessoa = new TipoPessoa();
		
		if(idTipoPessoa != null){
			tipoPessoa = dao.carrega(idTipoPessoa, TipoPessoa.class);
		}		
		return tipoPessoa;
	}

	@Override
	public void salvar(TipoPessoa tipoPessoa) {
		GenericDao<TipoPessoa> dao = (GenericDao<TipoPessoa>) getInstanceDao();	
		dao.adicionarOrAlterar(tipoPessoa);
	}

	@Override
	public void excluir(Integer[] ids) {
		GenericDao<TipoPessoa> dao = (GenericDao<TipoPessoa>) getInstanceDao();	
		Collection<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();	
		
		for(Integer i : ids ){			
			TipoPessoa tipoPessoa = new TipoPessoa();			
			if(i != null){
				dao = (GenericDao<TipoPessoa>) getInstanceDao();	
				tipoPessoa = dao.carrega(i, TipoPessoa.class);				
			}				
			if(!tipoPessoa.equals(null)){
				listaTipoPessoa.add(tipoPessoa);
			}			
		}	
		dao = (GenericDao<TipoPessoa>) getInstanceDao();
		dao.excluir(listaTipoPessoa);
	}

}
