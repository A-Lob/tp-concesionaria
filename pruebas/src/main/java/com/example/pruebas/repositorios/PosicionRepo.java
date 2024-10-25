package com.example.pruebas.repositorios;

import com.example.pruebas.models.Posicion;
import org.springframework.data.repository.CrudRepository;

public interface PosicionRepo extends CrudRepository<Posicion, Integer> {
}
