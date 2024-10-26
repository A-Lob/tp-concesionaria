package com.example.pruebas.repositories;

import com.example.pruebas.models.Interesado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoRepository extends CrudRepository<Interesado, Integer> {
    Interesado findById(int id);
}
