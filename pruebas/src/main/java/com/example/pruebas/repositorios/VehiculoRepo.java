package com.example.pruebas.repositorios;

import com.example.pruebas.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepo extends CrudRepository<Vehiculo, Long> {
}
