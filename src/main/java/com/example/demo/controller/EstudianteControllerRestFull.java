package com.example.demo.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.service.IEstudianteService;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

//PAQUETES PARA LINK
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



//SERVICIO -> Controller: Clase controller
@RestController
@RequestMapping(path="/estudiantes")
@CrossOrigin
public class EstudianteControllerRestFull {

	@Autowired
  private IEstudianteService estudianteService;
	
	@Autowired
	  private IMateriaService materiaService;
	
	//////////////CRUD///////////////////////////////////////
	

	//capaciadades
	//Get
	//http://localhost:8080/API/v1.0/Matricula/estudiante/buscar
	@GetMapping(path = "/self/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> buscar(@PathVariable(name = "id") Integer id) {
		
		//240> grupo satisfactorio
		//240 rescurso Estudiante encontrado satifactoriamente
		EstudianteTO estu = this.estudianteService.buscarTO(id);
		//Contrato de la API(`. documentacion, Swagger.io)
		
		Link link = linkTo(methodOn(EstudianteControllerRestFull.class).consultarMateriaPorId(estu.getId())).withRel("materias");
		
		
		Link link2 = linkTo(methodOn(EstudianteControllerRestFull.class).buscar(estu.getId())).withSelfRel();
		estu.add(link);
		estu.add(link2);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}
	
	
	  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<EstudianteLigeroTO> consultarEstudainteLigero(@PathVariable(name = "id")  Integer id){
		  EstudianteLigeroTO estu = this.estudianteService.consultarEstudianteLigeroTO(id);
		  
		  Link link = linkTo(methodOn(EstudianteControllerRestFull.class).buscar(estu.getId())).withRel("datos");
		  estu.add(link);
		  
		  return ResponseEntity.status(HttpStatus.OK).body(estu);
	  }
	
	  //http://localhost:8080/API/v1.0/Matricula/estudiante/guardar
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> guardar(@RequestBody Estudiante estudiante){
		  this.estudianteService.guardar(estudiante);
		  return ResponseEntity.status(HttpStatus.OK).body("Estudiante guaradado ");
	  }
	  
	  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> actualizar(@RequestBody Estudiante estudiante, @PathVariable(name = "id") Integer id) {
		estudiante.setId(id);
		  this.estudianteService.actualizar(estudiante);
		  return ResponseEntity.status(HttpStatus.OK).body("Estudiante actualizado ");
	  }
	  
	  @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String>  actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable(name = "id") Integer id) {
		  this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), estudiante.getId());
		  return ResponseEntity.status(HttpStatus.OK).body("Estudiante actualizado parcialmente");
	  }
	  
	  //PATHVARIABLE
	  @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String>  borrar(@PathVariable(name = "id") Integer id) {
		  this.estudianteService.borrarr(id);
		  return ResponseEntity.status(HttpStatus.OK).body("Estudiante eliminado");
	  }
	
	//////////////////////////////////////////////////////



  //REQUESTPARAM
  //consultarTodos?genero=F
  //consultarTodos?genero=F&edad=100
  @GetMapping(path = "/tmp", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Estudiante>> consultarTodos(@RequestParam(required = false, defaultValue = "M", name = "genero") String genero) {
      List<Estudiante> lista = this.estudianteService.consultarTodos(genero);
      
      HttpHeaders cabeceras = new HttpHeaders();
      cabeceras.add("mensaje_242", "lista consultada de manera satisfactoria");
      cabeceras.add("mensaje_info", "el sistema va a estar en mantenimiento el fin de semana");
      
      return new ResponseEntity<>(lista, cabeceras,242);
  }
  
  //HATEOAS
  //http://localhost:8080/API/v1.0/Matricula/estudiante/buscar
  @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EstudianteTO>> consultarTodosHateoas() {
      List<EstudianteTO> lista = this.estudianteService.consultarTodosTO();
      
      for(EstudianteTO est: lista) {
    	  Link link = linkTo(methodOn(EstudianteControllerRestFull.class).consultarMateriaPorId(est.getId())).withRel("materias");
    	  est.add(link);
      }
     
      return  ResponseEntity.status(HttpStatus.OK).body(lista);
  }
  
  @GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MateriaTO>> consultarMateriaPorId(@PathVariable(name = "id")  Integer id){
	  List<MateriaTO> lista = this.materiaService.buscarPorIdEstudiante(id);
	  System.out.println(lista);
	  return ResponseEntity.status(HttpStatus.OK).body(lista);
  }
  

}
