package com.example.pruebas.repositories;

import com.example.pruebas.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

    public interface MarcaRepository extends JpaRepository<Marca, Integer> {
        Marca findById(int id);
}
