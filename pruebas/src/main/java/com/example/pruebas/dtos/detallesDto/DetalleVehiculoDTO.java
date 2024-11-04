package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVehiculoDTO {
    private VehiculoDTO vehiculo;
    private List<PruebaDTO> pruebas;
    private List<PosicionDTO> posiciones;
    private ModeloDTO modelo;

}
