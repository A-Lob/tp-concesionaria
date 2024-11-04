package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.ModeloDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMarcaDTO {
    private List<ModeloDTO> modelos;
}
