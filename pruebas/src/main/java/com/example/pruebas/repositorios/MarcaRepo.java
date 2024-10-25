package com.example.pruebas.repositorios;

import com.example.pruebas.models.Marca;
import org.springframework.data.repository.CrudRepository;

public interface MarcaRepo extends CrudRepository<Marca, Integer> {
}
