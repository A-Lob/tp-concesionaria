package com.example.pruebas.repositorios;

import com.example.pruebas.models.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface EmpleadoRepo extends CrudRepository<Empleado, Long> {
}
