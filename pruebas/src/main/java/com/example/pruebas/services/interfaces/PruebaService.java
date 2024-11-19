package com.example.pruebas.services.interfaces;

import com.example.pruebas.dtos.NotificacionDTO;
import com.example.pruebas.dtos.PosicionDTO;
import com.example.pruebas.dtos.PromocionDTO;
import com.example.pruebas.dtos.PruebaDTO;
import com.example.pruebas.dtos.detallesDto.DetallePromocionDTO;
import com.example.pruebas.models.Prueba;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaService extends Service<Prueba, Integer> {

    void controlarVehiculo(PosicionDTO posicion);

}
