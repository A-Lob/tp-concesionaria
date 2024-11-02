package com.example.pruebas.services.interfaces;

import java.util.List;

public interface Service<T, K>{

    void add(T t);

    void update(T t);

    void delete(K id);

    T findById(K id);

    List<T> findAll();

}
