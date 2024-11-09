package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.EmpleadoDTO;
import com.example.pruebas.dtos.PruebaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleEmpleadoDTO {
    private EmpleadoDTO empleado;
    private List<PruebaDTO> pruebas;
}
