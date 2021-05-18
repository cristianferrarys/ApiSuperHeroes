package com.api.superheroes.dto;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SuperHeroeDto implements Serializable {

  //@NotBlank(message = "Ingrese el nombre del SuperHeroe")
  @NotBlank
  @Size(min = 3, message = "Cantidad Minima de caracteres es tres.")
  private String nombre;

  //@NotBlank(message = "Ingrese el Poder del SuperHeroe.")
  @NotBlank
  private String poder;

  @NotNull(message = "Ingrese si es Humano true o false")
  private boolean humano;

  private static final long serialVersionUID = 3221008787540864524L;

}
