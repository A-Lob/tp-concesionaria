package com.example.pruebas.repositories;

import com.example.pruebas.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface MarcaRepository extends JpaRepository<Marca, Integer> {
        Marca findById(int id);
}
