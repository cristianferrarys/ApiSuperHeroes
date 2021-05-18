package com.api.superheroes.repository;

import com.api.superheroes.entity.SuperHeroe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroeRepository extends JpaRepository<SuperHeroe, Long> {

  List<SuperHeroe> findByNombreContainingIgnoreCase(String nombre);

}
