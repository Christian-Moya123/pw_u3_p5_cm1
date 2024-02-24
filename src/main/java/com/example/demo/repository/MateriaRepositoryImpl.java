package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Materia;

@Transactional
@Repository
public class MateriaRepositoryImpl implements IMtaeriaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Materia> selccionarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		String jpql = "SELECT m FROM Materia m WHERE m.estudiante.id = :id";

		TypedQuery<Materia> myQuery = this.entityManager.createQuery(jpql,Materia.class);
		myQuery.setParameter("id", id);
		return myQuery.getResultList();
	}

}
