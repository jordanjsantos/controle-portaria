package br.com.controle.portaria.services;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;

public class LoginServiceImpl {
	private static GenericDao<Usuario> dao;

    private static synchronized GenericDao<Usuario> getInstance() {
        if (dao == null || dao.isSessionClosed()) {
        	dao = new GenericDao<Usuario>();
        }
        return dao;
    }
	
	public Boolean naoExisteUsuario(Usuario usuario) {
		GenericDao<Usuario> dao = getInstance();
		return dao.naoExisteUsuario(usuario);
	}

}
