package br.com.controle.portaria.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
public class UsuarioServiceImpl extends ServiceInterfaceAbstract<Usuario> {

	@Override
	public List<Usuario> listar() {
		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		listaUsuario = dao.listaTudo("from Usuario");
		return listaUsuario;
	}

	@Override
	public Usuario carregar(Integer id) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();
		Usuario usuario = new Usuario();

		if (id != null) {
			usuario = dao.carrega(id, Usuario.class);
		}
		return usuario;
	}

	public Usuario obterUsuarioPorLogin(String login) {
		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();
		List<Usuario> lUsuarios = dao.busca("user", login, Usuario.class);
		if (!lUsuarios.isEmpty()) {
			return lUsuarios.get(0);
		}
		return new Usuario();
	}

	@Override
	public void salvar(Usuario usuario) {
		System.out.println(this.getClass().getName() + "#############salvar#########");

		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();
		if (usuario != null && !"".equals(usuario.getSenha())) {
			usuario.setSenha(usuario.getSenhaCriptografadaMD5());
		}
		dao.adicionarOrAlterar(usuario);
	}

	@Override
	public void excluir(Integer[] ids) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<Usuario> dao = (GenericDao<Usuario>) getInstanceDao();	
		Collection<Usuario> listaUsuario = new ArrayList<Usuario>();	
		
		for(Integer i : ids){			
			Usuario usuario = new Usuario();			
			if(i != null){
				dao = (GenericDao<Usuario>) getInstanceDao();	
				usuario = dao.carrega(i, Usuario.class);				
			}				
			if(!usuario.equals(null)){
				listaUsuario.add(usuario);
			}			
		}	
		dao = (GenericDao<Usuario>) getInstanceDao();	
		dao.excluir(listaUsuario);
	}

}
