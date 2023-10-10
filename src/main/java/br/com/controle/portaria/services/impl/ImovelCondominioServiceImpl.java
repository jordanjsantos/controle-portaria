package br.com.controle.portaria.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.ImovelCondominio;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
@Service("imovelCondominioServiceImpl")
public class ImovelCondominioServiceImpl extends ServiceInterfaceAbstract<ImovelCondominio> {

	@Override
	public List<ImovelCondominio> listar() {
		GenericDao<ImovelCondominio> dao = (GenericDao<ImovelCondominio>) getInstanceDao();
		List<ImovelCondominio> listaImovelCond = new ArrayList<ImovelCondominio>();
		listaImovelCond = dao.listaTudo("from ImovelCondominio");
		return listaImovelCond;
	}

	@Override
	public ImovelCondominio carregar(Integer id) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<ImovelCondominio> dao = (GenericDao<ImovelCondominio>) getInstanceDao();
		ImovelCondominio imovelCondominio = new ImovelCondominio();

		if (id != null) {
			imovelCondominio = dao.carrega(id, ImovelCondominio.class);
		}

		return imovelCondominio;
	}

	@Override
	public void salvar(ImovelCondominio imovelCondominio) {
		System.out.println(this.getClass().getName() + "#############salvar#########");

		GenericDao<ImovelCondominio> dao = (GenericDao<ImovelCondominio>) getInstanceDao();
		dao.adicionarOrAlterar(imovelCondominio);
	}

	@Override
	public void excluir(Integer[] ids) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<ImovelCondominio> dao = (GenericDao<ImovelCondominio>) getInstanceDao();	
		Collection<ImovelCondominio> listaImovelCondominio = new ArrayList<ImovelCondominio>();	
		
		for(Integer i : ids ){			
			ImovelCondominio imovelCondominio = new ImovelCondominio();			
			if(i != null){
				dao = (GenericDao<ImovelCondominio>) getInstanceDao();	
				imovelCondominio = dao.carrega(i, ImovelCondominio.class);				
			}				
			if(!imovelCondominio.equals(null)){
				listaImovelCondominio.add(imovelCondominio);
			}			
		}	
		dao = (GenericDao<ImovelCondominio>) getInstanceDao();	
		dao.excluir(listaImovelCondominio);
	}
}
