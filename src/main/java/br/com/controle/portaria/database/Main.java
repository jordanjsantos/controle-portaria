package br.com.controle.portaria.database;

import java.util.ArrayList;
import java.util.Collection;

import br.com.controle.portaria.model.TipoPessoa;

public class Main {

	public static void main(String[] args) {
		GenericDao<TipoPessoa> dao = new GenericDao<TipoPessoa>();
		
		TipoPessoa tipoPessoaInsert = new TipoPessoa();
		tipoPessoaInsert.setDescricao("Entregador2");		
		dao.adicionarOrAlterar(tipoPessoaInsert);
		
		GenericDao<TipoPessoa> daoList = new GenericDao<TipoPessoa>();
		Collection<TipoPessoa> listaTipoPessoa = new ArrayList<TipoPessoa>();
		listaTipoPessoa = daoList.listaTudo("from TipoPessoa");
		
		for (TipoPessoa tipoPessoa : listaTipoPessoa) {
			System.out.println(tipoPessoa);
		}

	}

}
