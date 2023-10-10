package br.com.controle.portaria.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Pessoa;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
@Service("pessoaServiceImpl")
public class PessoaServiceImpl extends ServiceInterfaceAbstract<Pessoa> {

	@Override
	public List<Pessoa> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");

		GenericDao<Pessoa> dao = (GenericDao<Pessoa>) getInstanceDao();
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		listaPessoa = dao.listaTudo("from Pessoa");
		return listaPessoa;
	}

	@Override
	public Pessoa carregar(Integer id) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<Pessoa> dao = (GenericDao<Pessoa>) getInstanceDao();
		Pessoa pessoa = new Pessoa();

		if (id != null) {
			pessoa = dao.carrega(id, Pessoa.class);
		}
		return pessoa;
	}

	@Override
	public List<Pessoa> carregar(String nome) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<Pessoa> dao = (GenericDao<Pessoa>) getInstanceDao();
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();

		if (nome != null && !"".equals(nome)) {
			listPessoa = dao.busca("nome", nome, Pessoa.class);
		}
		return listPessoa;
	}

	@Override
	public void salvar(Pessoa pessoa) {
		System.out.println(this.getClass().getName() + "#############salvar#########");
		GenericDao<Pessoa> dao = (GenericDao<Pessoa>) getInstanceDao();
		dao.adicionarOrAlterar(pessoa);
	}

	@Override
	public void excluir(Integer[] ids) {
		System.out.println(this.getClass().getName() + "#############excluir#########");	
		GenericDao<Pessoa> dao = (GenericDao<Pessoa>) getInstanceDao();
		Collection<Pessoa> listaPessoa = new ArrayList<Pessoa>();	
		
		for(Integer i : ids ){			
			Pessoa pessoa = new Pessoa();			
			if(i != null){
				dao = (GenericDao<Pessoa>) getInstanceDao();
				pessoa = dao.carrega(i, Pessoa.class);				
			}				
			if(!pessoa.equals(null)){
				listaPessoa.add(pessoa);
			}			
		}	
		dao = (GenericDao<Pessoa>) getInstanceDao();	
		dao.excluir(listaPessoa);
	}

}
