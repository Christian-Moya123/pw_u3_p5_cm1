package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMtaeriaRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

@Service
public class MateriaServiceImpl  implements IMateriaService{
	
	@Autowired
	private IMtaeriaRepository materiaRepository;

	@Override
	public List<MateriaTO> buscarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		List<Materia> lista = this.materiaRepository.selccionarPorIdEstudiante(id);
		List<MateriaTO> listaFinal = new ArrayList<>();
		
		for(Materia mat: lista) {
			listaFinal.add(this.convertir(mat));
		}
		
		return listaFinal;
	}
	
	private MateriaTO convertir(Materia mate) {
		MateriaTO mateTo = new MateriaTO();
		mateTo.setCreditos(mate.getCreditos());
		mateTo.setId(mate.getId());
		mateTo.setNombre(mate.getNombre());
		return mateTo;
				
		
	}

}
