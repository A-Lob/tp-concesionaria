package com.example.pruebas.repositorios;

import com.example.pruebas.models.Prueba;
import org.springframework.data.repository.CrudRepository;

public interface PruebaRepo extends CrudRepository<Prueba, Integer> {
}
