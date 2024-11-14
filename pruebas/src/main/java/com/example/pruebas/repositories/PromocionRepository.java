package com.example.pruebas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pruebas.models.Promocion;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    Promocion findById(int id);
}
