package br.com.controle.portaria.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;

@Service
public abstract class ServiceInterfaceAbstract<T> implements ServiceInterface<T>{
		private static GenericDao<?> dao;
		
	    protected static synchronized GenericDao<?> getInstanceDao() {
	        if (dao == null || dao.isSessionClosed()) {
	        	dao = new GenericDao<>();
	        }
	        return dao;
	    }
	    
		public List<T> carregar(String nome){
			return null;
		};
		
		public void salvar(T entity, Usuario usuario) {
			
		};
		
		
}
