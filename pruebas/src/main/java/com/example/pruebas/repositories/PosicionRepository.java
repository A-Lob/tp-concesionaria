package com.example.pruebas.repositories;

import com.example.pruebas.models.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
}
