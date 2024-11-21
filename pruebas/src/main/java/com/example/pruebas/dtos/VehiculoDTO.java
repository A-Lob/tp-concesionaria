package com.example.pruebas.dtos;

import com.example.pruebas.repositories.PruebaRepository;
import com.example.pruebas.services.implementations.PruebaServiceImpl;
import com.example.pruebas.services.interfaces.PruebaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
    private int id;
    private String patente;
    private int anio;



}
