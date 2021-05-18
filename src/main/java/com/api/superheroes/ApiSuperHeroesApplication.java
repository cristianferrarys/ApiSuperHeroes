package com.api.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * @author CristianF
 *
 */

@SpringBootApplication
@EnableCaching
public class ApiSuperHeroesApplication {
  public static void main(String[] args) {
    SpringApplication.run(ApiSuperHeroesApplication.class, args);
  }

}
