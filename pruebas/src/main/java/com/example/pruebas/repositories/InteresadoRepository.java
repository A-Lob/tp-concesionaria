package com.example.pruebas.repositories;

import com.example.pruebas.models.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, Integer> {
    Interesado findById(int id);
}
