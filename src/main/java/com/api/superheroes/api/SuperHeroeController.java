package com.api.superheroes.api;

import com.api.superheroes.dto.SuperHeroeDto;
import com.api.superheroes.entity.SuperHeroe;
import com.api.superheroes.exception.ServiceException;
import com.api.superheroes.service.SuperHeroeService;
import com.api.superheroes.utils.TiempoTranscurrido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * 
 * @author CristianF
 *
 */
@RestController
@RequestMapping("/v1")
@Api(tags = "superHeroes")
public class SuperHeroeController {

  final public static Logger log = LoggerFactory.getLogger(SuperHeroeController.class);
  @Autowired
  private SuperHeroeService superHeroeService;

  @GetMapping
  @TiempoTranscurrido(name = "listar")
  @ApiOperation(value = "Listar SuperHeroes", notes = "Servicio para listar SuperHeroes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Error al consultar por en la DB.")})
  public ResponseEntity<?> listar() {
    Map<String, Object> response = new HashMap<>();
    try {
      List<SuperHeroe> listHeroes = superHeroeService.listHeroe();
      return new ResponseEntity<List<SuperHeroe>>(listHeroes, HttpStatus.ACCEPTED);
    } catch (ServiceException e) {
      response.put("Mensaje", "Error al consultar por en la DB.");
      response.put("error", e);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @TiempoTranscurrido
  @GetMapping("/{id}")
  @ApiOperation(value = "Buscar por Id", notes = "Servicio para buscar por id de SuperHeroes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Error al consultar por en la DB."),
      @ApiResponse(code = 404, message = "El SuperHeroe con ID:14 no existe ")})
  public ResponseEntity<?> findById(@PathVariable(name = "id") final Long id) {
    SuperHeroe superHeroe = null;
    Map<String, Object> response = new HashMap<>();
    try {
      superHeroe = superHeroeService.findByIdHeroe(id);
    } catch (ServiceException ex) {
      response.put("Mensaje", "Error al consultar por en la DB.");
      response.put("error", ex.getMessage() + ": " + ex);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (superHeroe == null) {
      response.put("Mensaje", "El SuperHeroe con ID:" + id + " no existe ");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SuperHeroe>(superHeroe, HttpStatus.ACCEPTED);
  }


  @GetMapping("/search/{nombre}")
  @ApiOperation(value = "Buscar por Nombre", notes = "Servicio para buscar por nombre de SuperHeroes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Error al consultar por en la DB."),
      @ApiResponse(code = 400, message = "La busqueda debe contener al menos 3 caracteres.")})  
  public ResponseEntity<?> findByNombre(@PathVariable(name = "nombre") final String nombre) {

    List<SuperHeroe> superHeroeList = null;
    Map<String, Object> response = new HashMap<>();

    if (nombre.length() < 3 || nombre.isBlank()) {
      response.put("Mensaje", "La busqueda debe contener al menos 3 caracteres.");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    try {
      superHeroeList = superHeroeService.findByNombreHeroe(nombre);
    } catch (ServiceException ex) {
      response.put("Mensaje", "Error al consultar por en la DB.");
      response.put("error", ex.getMessage() + ": " + ex);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<SuperHeroe>>(superHeroeList, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Actualizar SuperHeroes", notes = "Servicio para actualizar un SuperHeroes")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Ok"), @ApiResponse(code = 500, message = "Error al modificar el SuperHeroe por en la DB."),
      @ApiResponse(code = 400, message = "Error en el campo de envio.")})    
  public ResponseEntity<?> updateSuperHeroe(@Valid @RequestBody SuperHeroeDto superHeroeDto, BindingResult result,
      @PathVariable(name = "id") final Long id) {
    SuperHeroe superHeroeModificado = null;
    Map<String, Object> response = new HashMap<>();
    SuperHeroe superHeroeActual;
    if (result.hasErrors()) {
      result.getFieldErrors().forEach(error -> {
        response.put(error.getField(), "Error en el campo " + error.getField() + ":" + error.getDefaultMessage());
      });
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    try {
      superHeroeActual = superHeroeService.findByIdHeroe(id);
      superHeroeActual.setNombre(superHeroeDto.getNombre());
      superHeroeActual.setPoder(superHeroeDto.getPoder());
      superHeroeActual.setHumano(superHeroeDto.isHumano());
      superHeroeModificado = superHeroeService.updateHeroe(superHeroeActual);
    } catch (ServiceException e) {
      response.put("Mensaje", "Error al modificar el SuperHeroe por en la DB.");
      response.put("error", e.getMessage() + ": " + e);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<SuperHeroe>(superHeroeModificado, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Eliminar SuperHeroe", notes = "Servicio para eliminar un SuperHeroes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Error al eliminar el SuperHeroe por en la DB."),
      @ApiResponse(code = 404, message = "El SuperHeroe con ID: XX no existe ")})      
  public ResponseEntity<?> deleteById(@PathVariable(name = "id") final Long id) {
    SuperHeroe superHeroe = null;
    Map<String, Object> response = new HashMap<>();
    try {
      superHeroe = superHeroeService.findByIdHeroe(id);
      superHeroeService.deleteHeroe(id);
    } catch (ServiceException ex) {
      response.put("Mensaje", "Error al eliminar por en la DB. id:" + id);
      response.put("error", ex.getMessage() + ": " + ex);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (superHeroe == null) {
      response.put("Mensaje", "El SuperHeroe con ID:" + id + " no existe ");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    response.put("Mensaje", "Se elimino el SuperHeroe con ID:" + id);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

}
