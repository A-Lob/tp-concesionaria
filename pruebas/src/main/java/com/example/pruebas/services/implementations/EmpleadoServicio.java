package com.example.pruebas.services.implementations;

import com.example.pruebas.models.Empleado;
import com.example.pruebas.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmpleadoServicio {

    private final EmpleadoRepository empleado;
    @Autowired
    public EmpleadoServicio(EmpleadoRepository empleado) {
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
