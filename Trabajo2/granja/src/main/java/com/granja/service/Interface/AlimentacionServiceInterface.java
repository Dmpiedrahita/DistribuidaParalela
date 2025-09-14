package com.granja.service.Interface;

import com.granja.model.Alimentacion;

import java.util.List;

public interface AlimentacionServiceInterface {
    List<Alimentacion> listarAlimentaciones();
    void guardarAlimentacion(Alimentacion alimentacion);
    Alimentacion buscarPorId(int id);
    void eliminarAlimentacion(int id);
}