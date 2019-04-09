package br.com.reciclagem.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.reciclagem.web.entidade.MaterialEntidade;

public class MaterialRepository  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	@Inject
	public MaterialRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaterialEntidade> getFindAll() throws Exception{
		Query query = manager.createQuery("from MaterialEntidade"); 
		return (List<MaterialEntidade>)query.getResultList();

	}
	
	public void gravar(MaterialEntidade material){
		manager.getTransaction().begin();
		manager.persist(material);
		manager.getTransaction().commit();
		manager.close();
	}
	
	
	
	
}
