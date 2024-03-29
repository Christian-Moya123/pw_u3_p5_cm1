package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Estudiante estudiante) {
        this.entityManager.persist(estudiante);
    }
    @Override
    public void actualizar(Estudiante estudiante) {
        this.entityManager.merge(estudiante);

    }
    @Override
    public void actualizarParcial(String apellido, String nombre, Integer id) {
        //SQL: UPDATE estudiante  e set e.estu_nombre=:valor, e.estu_apellido=:valor2
        Query query=this.entityManager
                .createQuery("UPDATE Estudiante e SET e.nombre = :valor1, e.apellido =:valor2 WHERE e.id =:valor3");
        query.setParameter("valor1", nombre);
        query.setParameter("valor2", apellido);
        query.setParameter("valor3", id);
        query.executeUpdate();

    }
    @Override
    public Estudiante seleccionar(Integer id) {
        return this.entityManager.find(Estudiante.class, id);
    }
    @Override
    public void eliminar(Integer id) {
        this.entityManager.remove(this.seleccionar(id));
    }
	@Override
	public List<Estudiante> consultarTodos(String genero) {
	    String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero"; 
	    TypedQuery<Estudiante> myQuery = this.entityManager.createQuery(jpql, Estudiante.class);
	    myQuery.setParameter("genero", genero); 
	    return myQuery.getResultList(); 
	}

}
