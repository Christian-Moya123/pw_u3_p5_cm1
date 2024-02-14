package com.example.demo.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;

public class EstudianteLigeroTO  extends RepresentationModel<EstudianteLigeroTO> implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	
	
	//set y get
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	
	
	

}
