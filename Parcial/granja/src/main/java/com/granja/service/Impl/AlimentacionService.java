package com.granja.service.Impl;

import com.granja.model.Alimentacion;
import com.granja.repository.AlimentacionRepository;
import com.granja.service.Interface.AlimentacionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlimentacionService implements AlimentacionServiceInterface {

    private final AlimentacionRepository alimentacionRepository;

    @Override
    public List<Alimentacion> listarAlimentaciones() {
        return alimentacionRepository.listarAlimentaciones();
    }

    @Override
    public void guardarAlimentacion(Alimentacion alimentacion) {
        alimentacionRepository.guardarAlimentacion(alimentacion);
    }

    @Override
    public Alimentacion buscarPorId(int id) {
        return alimentacionRepository.buscarPorId(id);
    }

    @Override
    public void eliminarAlimentacion(int id) {
        alimentacionRepository.eliminarAlimentacion(id);
    }
}

