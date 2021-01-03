package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Departamento;

public class DepartamentoDao extends GenericDao<Departamento, Integer>{

	public DepartamentoDao(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Departamento> listDepartamentoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Departamento u "
														+ "LEFT JOIN FETCH u.departamento "
														+ "LEFT JOIN FETCH u.roles "
														+ "WHERE u.ativo =: ativo");
		query.setParameter("ativo", true);
		query.setFirstResult(numeroPagina);
		query.setMaxResults(defaultPagina);
		
		listaDepartamento = query.getResultList();
		return listaDepartamento;
	}
	
	

}
