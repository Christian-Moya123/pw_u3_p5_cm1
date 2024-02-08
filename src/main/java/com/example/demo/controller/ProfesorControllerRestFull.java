package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping(path="/profesores")
public class ProfesorControllerRestFull {
	  @Autowired
	  private IProfesorService profesorService;
	  
	  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	  public ResponseEntity<Profesor>  buscar(@PathVariable(name = "id") Integer id) {
	      Profesor prof = this.profesorService.seleccionar(id);
	      return ResponseEntity.status(HttpStatus.OK).body(prof);
	      
	  } 
	  
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public void guardar(@RequestBody Profesor profesor){
	        this.profesorService.insertar(profesor);
	  }
	  
	  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public void actualizar(@RequestBody Profesor profesor, @PathVariable(name = "id") Integer id) {
		  profesor.setId(id);
	        this.profesorService.actualizar(profesor);
	  }
	  
	  @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public void actualizarParcial(@RequestBody Profesor profesor, @PathVariable(name = "id") Integer id) {
	        this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), profesor.getId());
	  }
	  
	  @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public void borrar(@PathVariable(name = "id") Integer id) {
	        this.profesorService.eliminar(id);
	  }
	  
	  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<Profesor>>  consultarTodos(@RequestParam(name = "genero") String genero) {
	        List<Profesor> lista = this.profesorService.consultarTodos(genero);
	 
	        HttpHeaders cabeceras = new HttpHeaders();
	        cabeceras.add("mensaje_242", "lista consultada de manera satisfactoria");
	        return new ResponseEntity<>(lista, cabeceras,242);
	  }

}
