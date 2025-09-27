package com.granja.service.Impl;

import com.granja.model.Porcino;
import com.granja.repository.PorcinoRepository;
import com.granja.service.Interface.PorcinoServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PorcinoService implements PorcinoServiceInterface {

    private PorcinoRepository porcinoRepository;

    @Override
    public List<Porcino> listarPorcinos() {
        return porcinoRepository.findAll();
    }

    @Override
    public void guardarPorcino(Porcino porcino) {
        porcinoRepository.insert(porcino);
    }

    @Override
    public Porcino buscarPorId(int id) {
        return porcinoRepository.findById(id);
    }

    @Override
    public void eliminarPorcino(int id) {
        porcinoRepository.delete(id);
    }
}
