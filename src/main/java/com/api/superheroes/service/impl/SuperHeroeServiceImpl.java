package com.api.superheroes.service.impl;

import com.api.superheroes.entity.SuperHeroe;
import com.api.superheroes.exception.ServiceException;
import com.api.superheroes.repository.SuperHeroeRepository;
import com.api.superheroes.service.SuperHeroeService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class SuperHeroeServiceImpl implements SuperHeroeService {

  @Autowired
  private SuperHeroeRepository superHeroeRepository;

  @Override
  @Cacheable(cacheNames = "heroes")
  @Transactional(readOnly = true)
  public List<SuperHeroe> listHeroe() throws ServiceException {
    try {
      return superHeroeRepository.findAll();
    } catch (final DataAccessException ex) {
      throw new ServiceException("Error DB listar Heroes", ex);
    }
  }

  @Override
  @Cacheable(cacheNames = "heroes", key = "#idHeroe")
  @Transactional(readOnly = true)
  public SuperHeroe findByIdHeroe(Long idHeroe) throws ServiceException {
    try {
      return superHeroeRepository.findById(idHeroe).orElse(null);
    } catch (final DataAccessException ex) {
      throw new ServiceException("Error buscar por el ID: " + idHeroe, ex);
    }

  }

  @Override
  @Cacheable(cacheNames = "heroes")
  @Transactional(readOnly = true)
  public List<SuperHeroe> findByNombreHeroe(String nombreHeroe) throws ServiceException {
    try {
      return superHeroeRepository.findByNombreContainingIgnoreCase(nombreHeroe);
    } catch (final DataAccessException ex) {
      throw new ServiceException("Error al buscar por el nombre: " + nombreHeroe, ex);
    }

  }

  @Override
  @CachePut(cacheNames = "heroes", key = "#superHeroe.id")
  @Transactional
  public SuperHeroe updateHeroe(SuperHeroe superHeroe) throws ServiceException {
    try {
      return superHeroeRepository.save(superHeroe);
    } catch (final DataAccessException ex) {
      throw new ServiceException("Error al actualizar en la DB", ex);
    }

  }

  @Override
  @CacheEvict(cacheNames = "heroes", key = "#idHeroe")
  @Transactional
  public void deleteHeroe(Long idHeroe) throws ServiceException {
    try {
      superHeroeRepository.deleteById(idHeroe);
    } catch (final DataAccessException ex) {
      throw new ServiceException("Error al eliminar por ID:" + idHeroe, ex);
    }

  }

}
