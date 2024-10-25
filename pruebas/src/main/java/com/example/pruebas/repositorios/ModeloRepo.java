package com.example.pruebas.repositorios;

import com.example.pruebas.models.Modelo;
import org.springframework.data.repository.CrudRepository;

public interface ModeloRepo extends CrudRepository<Modelo, Integer> {
}
