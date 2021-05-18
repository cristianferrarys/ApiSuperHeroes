package com.api.superheroes.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.api.superheroes.entity.SuperHeroe;
import com.api.superheroes.exception.ServiceException;
import com.api.superheroes.repository.SuperHeroeRepository;
import com.api.superheroes.service.SuperHeroeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SuperHeroeServiceImplTest {

  SuperHeroeRepository repo;
  SuperHeroeService servicio;

  @BeforeEach
  void setUp() {
    repo = mock(SuperHeroeRepository.class);
    servicio = new SuperHeroeServiceImpl(repo);
  }

  @Test
  void testFindByIdHeroe() throws ServiceException {
    List<SuperHeroe> listSuperHeroes = Arrays.asList(new SuperHeroe(1L, "Batman", "", true), new SuperHeroe(2L, "Spiderman", "", true),
        new SuperHeroe(3L, "SuperMan", "", true));
    when(repo.findAll()).thenReturn(listSuperHeroes);
    List<SuperHeroe> listSuperHeroesActual = servicio.listHeroe();
    assertEquals(3, listSuperHeroesActual.size());
  }

  @Test
  void testFindByNombreHeroe() throws ServiceException {
    List<SuperHeroe> listSuperHeroes = Arrays.asList(new SuperHeroe(1L, "Batman", "", true), new SuperHeroe(2L, "Spiderman", "", true),
        new SuperHeroe(3L, "SuperMan", "", true));
    String nombre = "man";
    when(repo.findByNombreContainingIgnoreCase(nombre)).thenReturn(listSuperHeroes);
    List<SuperHeroe> listSuperHeroesActual = servicio.findByNombreHeroe(nombre);
    System.out.println("lista: " + listSuperHeroesActual);
    assertEquals(3, listSuperHeroes.size());
  }

  @Test
  void testUpdateHeroe() throws ServiceException {
    SuperHeroe superHeroes = new SuperHeroe(1L, "SuperMan", "", false);
    when(repo.save(superHeroes)).thenReturn(superHeroes);
    SuperHeroe superHeroesActual = servicio.updateHeroe(superHeroes);
    assertEquals(1L, superHeroesActual.getId());
  }

}
