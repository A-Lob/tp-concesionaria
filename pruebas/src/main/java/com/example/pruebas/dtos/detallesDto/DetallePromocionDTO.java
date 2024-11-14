package com.example.pruebas.dtos.detallesDto;

import com.example.pruebas.dtos.InteresadoDTO;
import com.example.pruebas.dtos.PromocionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePromocionDTO {
    private PromocionDTO promocion;
    private List<InteresadoDTO> interesados;
    private List<DetalleVehiculoDTO> vehiculos;
}
