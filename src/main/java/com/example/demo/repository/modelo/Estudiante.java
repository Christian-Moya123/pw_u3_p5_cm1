package com.example.demo.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.service.to.MateriaTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
//@JsonIgnoreProperties(value = "materias")
public class Estudiante {
	
	@Id
	@GeneratedValue(generator = "seq_estudiante", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_estudiante", sequenceName = "seq_estudiante", allocationSize = 1)
	@Column(name = "estu_id")
	private Integer id;
	
	@Column(name = "estu_nombre")
	private String nombre;
	
	@Column(name = "estu_apellido")
	private String apellido;
	
	@Column(name = "estu_genero")
	private String genero;
	
	@Column(name = "estu_fecha_nacimiento")
	private LocalDateTime fechaNacimietno;
	
	@OneToMany(mappedBy = "estudiante")
	private List<Materia> materias;

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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDateTime getFechaNacimietno() {
		return fechaNacimietno;
	}

	public void setFechaNacimietno(LocalDateTime fechaNacimietno) {
		this.fechaNacimietno = fechaNacimietno;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	

}
