package br.com.controle.portaria.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.controle.portaria.database.GenericDao;
import br.com.controle.portaria.model.Veiculo;
import br.com.controle.portaria.services.ServiceInterfaceAbstract;

@SuppressWarnings("unchecked")
public class VeiculoServiceImpl extends ServiceInterfaceAbstract<Veiculo> {

	@Override
	public List<Veiculo> listar() {
		System.out.println(this.getClass().getName() + "#############listar#########");
		GenericDao<Veiculo> dao = (GenericDao<Veiculo>) getInstanceDao();
		List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
		listaVeiculo = dao.listaTudo("from Veiculo");
		return listaVeiculo;
	}

	@Override
	public Veiculo carregar(Integer id) {
		System.out.println(this.getClass().getName() + "#############carregar#########");

		GenericDao<Veiculo> dao = (GenericDao<Veiculo>) getInstanceDao();
		Veiculo veiculo = new Veiculo();

		if (id != null) {
			veiculo = dao.carrega(id, Veiculo.class);
		}

		return veiculo;
	}

	@Override
	public void salvar(Veiculo veiculo) {
		System.out.println(this.getClass().getName() + "#############salvar#########");

		GenericDao<Veiculo> dao = (GenericDao<Veiculo>) getInstanceDao();
		dao.adicionarOrAlterar(veiculo);
	}

	@Override
	public void excluir(Integer[] ids) {
		System.out.println(this.getClass().getName() + "#############excluir#########");
		GenericDao<Veiculo> dao = (GenericDao<Veiculo>) getInstanceDao();
		Collection<Veiculo> listaVeiculo = new ArrayList<Veiculo>();

		for (Integer i : ids) {
			Veiculo veiculo = new Veiculo();
			if (i != null) {
				dao = (GenericDao<Veiculo>) getInstanceDao();
				veiculo = dao.carrega(i, Veiculo.class);
			}
			if (!veiculo.equals(null)) {
				listaVeiculo.add(veiculo);
			}
		}
		dao = (GenericDao<Veiculo>) getInstanceDao();
		dao.excluir(listaVeiculo);
	}

}
