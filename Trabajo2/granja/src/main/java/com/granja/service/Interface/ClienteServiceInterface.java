package com.granja.service.Interface;

import com.granja.model.Cliente;
import com.granja.model.Reporte;

import java.util.List;

public interface ClienteServiceInterface {
    List<Cliente> listarClientes();
    void guardarCliente(Cliente cliente);
    Cliente buscarPorCedula(String cedula);
    void eliminarCliente(String cedula);
    List<Reporte> listarClientesConPorcinos();
}