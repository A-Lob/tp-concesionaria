package com.example.pruebas.repositories;

import com.example.pruebas.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    Vehiculo findById(int id);
}
