package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService{
	
	@Autowired
	private IEstudianteRepository estudainteRepo;

	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudainteRepo.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudainteRepo.actualizar(estudiante);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub
		this.estudainteRepo.actualizarParcial(apellido, nombre, id);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudainteRepo.seleccionar(id);
	}

	@Override
	public void borrarr(Integer id) {
		// TODO Auto-generated method stub
		this.estudainteRepo.eliminar(id);
	}

	@Override
	public List<Estudiante> consultarTodos(String genero) {
		// TODO Auto-generated method stub
		return this.estudainteRepo.consultarTodos(genero);
	}

	@Override
	public List<EstudianteTO> consultarTodosTO() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudainteRepo.consultarTodos("M");
		List<EstudianteTO> listaFinal = new ArrayList<>();
		for(Estudiante est:lista) {
			listaFinal.add(this.convertir(est));
		}
	
		return listaFinal;
	}
	

	@Override
	public EstudianteTO buscarTO(Integer id) {
		// TODO Auto-generated method stub
		return this.convertir(this.estudainteRepo.seleccionar(id));
	}
	
	@Override
	public EstudianteLigeroTO consultarEstudianteLigeroTO(Integer id) {
		// TODO Auto-generated method stub
		return this.convertirLigeroTO(this.estudainteRepo.seleccionar(id));
	}

	
	
	public EstudianteLigeroTO convertirLigeroTO(Estudiante est) {
		// TODO Auto-generated method stub
		EstudianteLigeroTO estuLigero = new EstudianteLigeroTO();
		estuLigero.setApellido(est.getApellido());
		estuLigero.setId(est.getId());
		estuLigero.setNombre(est.getNombre());
		return estuLigero;
	}

	
	private EstudianteTO convertir(Estudiante estu) {
		EstudianteTO estuTo = new EstudianteTO();
		estuTo.setApellido(estu.getApellido());
		estuTo.setFechaNacimietno(estu.getFechaNacimietno());
		estuTo.setGenero(estu.getGenero());
		estuTo.setNombre(estu.getNombre());
		estuTo.setId(estu.getId());
		estuTo.setCarrera(estu.getCarrera());
		estuTo.setDireccion(estu.getDireccion());
		estuTo.setEmail(estu.getEmail());
		estuTo.setPromedio(estu.getPromedio());
		estuTo.setTelefono(estu.getTelefono());
		return estuTo;
				
		
	}

	
	

}
