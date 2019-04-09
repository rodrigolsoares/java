package br.com.reciclagem.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.reciclagem.web.entidade.MenuEntidade;

public class MenuRepository  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	@Inject
	public MenuRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuEntidade> getFindAll() throws Exception{
		Query query = manager.createQuery("from MenuEntidade"); 
		return (List<MenuEntidade>)query.getResultList();

	}
	
	
	
	
}
