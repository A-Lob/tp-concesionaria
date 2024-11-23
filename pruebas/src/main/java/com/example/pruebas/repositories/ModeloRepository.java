package com.example.pruebas.repositories;

import com.example.pruebas.models.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    Modelo findById(int id);
}
