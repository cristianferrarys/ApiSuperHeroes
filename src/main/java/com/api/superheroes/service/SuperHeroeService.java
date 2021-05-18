package com.api.superheroes.service;

import com.api.superheroes.entity.SuperHeroe;
import com.api.superheroes.exception.ServiceException;

import java.util.List;

public interface SuperHeroeService {

  List<SuperHeroe> listHeroe() throws ServiceException;

  SuperHeroe findByIdHeroe(Long idHeroe) throws ServiceException;

  List<SuperHeroe> findByNombreHeroe(String nombreHeroe) throws ServiceException;

  SuperHeroe updateHeroe(SuperHeroe superHeroe) throws ServiceException;

  void deleteHeroe(Long idHeroe) throws ServiceException;

}
