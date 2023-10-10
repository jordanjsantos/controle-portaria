package br.com.controle.portaria.services.impl;

import java.util.List;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
public class LoginServiceImpl extends ServiceInterfaceAbstract<Usuario> {
	
	public Boolean naoExisteUsuario(Usuario usuario) {
		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();
		return dao.naoExisteUsuario(usuario);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario carregar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer[] ids) {
		// TODO Auto-generated method stub
		
	}
}
