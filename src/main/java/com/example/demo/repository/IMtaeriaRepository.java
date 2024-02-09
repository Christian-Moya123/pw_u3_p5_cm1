package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Materia;

public interface IMtaeriaRepository {
	
	public List<Materia> selccionarPorIdEstudiante(Integer id);

}
