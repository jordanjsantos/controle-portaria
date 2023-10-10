package br.com.controle.portaria.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.controle.portaria.controller.web.impl.UsuarioControllerImpl;
import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.ControleRegistro;
import br.com.controle.portaria.model.Usuario;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
@Service("registroServiceImpl")
public class RegistroServiceImpl extends ServiceInterfaceAbstract<ControleRegistro> {

	@Override
	public List<ControleRegistro> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");

		GenericDao<ControleRegistro> dao = (GenericDao<ControleRegistro>) getInstanceDao();
		List<ControleRegistro> listaControleRegistro = new ArrayList<ControleRegistro>();
		listaControleRegistro = dao.listaTudo("from ControleRegistro");

		return listaControleRegistro;
	}

	@Override
	public ControleRegistro carregar(Integer id) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<ControleRegistro> dao = (GenericDao<ControleRegistro>) getInstanceDao();
		ControleRegistro controleRegistro = new ControleRegistro();

		if (id != null) {
			controleRegistro = dao.carrega(id, ControleRegistro.class);
		}
		return controleRegistro;
	}
	
	@Override
	public void salvar(ControleRegistro entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void salvar(ControleRegistro controleRegistro, Usuario usuario) {
		System.out.println(this.getClass().getName() + "#############salvar#########");

		GenericDao<ControleRegistro> dao = (GenericDao<ControleRegistro>) getInstanceDao();

		Usuario usuarioFinal = new UsuarioControllerImpl().obterUsuarioPorLogin(usuario.getUser());
		controleRegistro.setDataHoraReg(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		controleRegistro.setUsuario(usuarioFinal);

		dao.adicionarOrAlterar(controleRegistro);
	}

	@Override
	public void excluir(Integer[] ids) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<ControleRegistro> dao = (GenericDao<ControleRegistro>) getInstanceDao();
		Collection<ControleRegistro> listaControleRegistro = new ArrayList<ControleRegistro>();	
		
		for(Integer i : ids ){			
			ControleRegistro controleRegistro = new ControleRegistro();			
			if(i != null){
				dao = (GenericDao<ControleRegistro>) getInstanceDao();	
				controleRegistro = dao.carrega(i, ControleRegistro.class);				
			}				
			if(!controleRegistro.equals(null)){
				listaControleRegistro.add(controleRegistro);
			}			
		}	
		dao = (GenericDao<ControleRegistro>) getInstanceDao();	
		dao.excluir(listaControleRegistro);
	}


}
