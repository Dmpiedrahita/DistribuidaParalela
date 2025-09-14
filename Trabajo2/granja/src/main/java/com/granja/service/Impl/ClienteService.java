package com.granja.service.Impl;

import com.granja.model.Cliente;
import com.granja.model.Reporte;
import com.granja.repository.ClienteRepository;
import com.granja.service.Interface.ClienteServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    @Override
    public void eliminarCliente(String cedula) {
        clienteRepository.deleteByCedula(cedula);
    }

    @Override
    public List<Reporte> listarClientesConPorcinos() {
        return clienteRepository.reporte();
    }
}

