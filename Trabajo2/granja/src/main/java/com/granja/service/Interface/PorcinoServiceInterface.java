package com.granja.service.Interface;

import com.granja.model.Porcino;

import java.util.List;

public interface PorcinoServiceInterface {

    List<Porcino> listarPorcinos();
    void guardarPorcino(Porcino porcino);
    Porcino buscarPorId(int id);
    void eliminarPorcino(int id);

}
