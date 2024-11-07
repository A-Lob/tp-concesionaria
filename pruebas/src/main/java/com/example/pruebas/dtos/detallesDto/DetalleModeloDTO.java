package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.MarcaDTO;
import com.example.pruebas.dtos.ModeloDTO;
import com.example.pruebas.dtos.VehiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleModeloDTO {
    private ModeloDTO modelo;
    private List<VehiculoDTO> vehiculos;
    private MarcaDTO marca;

}
