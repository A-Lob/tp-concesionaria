package com.example.pruebas.repositories;

import com.example.pruebas.models.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
}
