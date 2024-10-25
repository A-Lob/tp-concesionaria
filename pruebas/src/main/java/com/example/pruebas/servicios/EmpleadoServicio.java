package com.example.pruebas.servicios;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.repositorios.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmpleadoServicio {

    private final EmpleadoRepo empleado;
    @Autowired
    public EmpleadoServicio(EmpleadoRepo empleado) {
        this.empleado = empleado;
    }
    public void guardar(Empleado e){
        empleado.save(e);
    }
    public void eliminar(long id){
        empleado.deleteById(id);
    }
    public Optional<Empleado> idBuscar(long id){
        return empleado.findById(id);
    }
    public List<Empleado> listar(){
       return (List<Empleado>) empleado.findAll();
    }

}
