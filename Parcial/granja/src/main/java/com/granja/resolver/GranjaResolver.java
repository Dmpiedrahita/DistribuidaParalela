package com.granja.resolver;

import com.granja.model.Cliente;
import com.granja.model.Porcino;
import com.granja.model.Alimentacion;
import com.granja.model.Reporte;
import com.granja.service.Interface.ClienteServiceInterface;
import com.granja.service.Interface.PorcinoServiceInterface;
import com.granja.service.Interface.AlimentacionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GranjaResolver {

    private final ClienteServiceInterface clienteService;
    private final PorcinoServiceInterface porcinoService;
    private final AlimentacionServiceInterface alimentacionService;

    @QueryMapping
    public List<Cliente> clientes() {
        return clienteService.listarClientes();
    }

    @QueryMapping
    public Cliente clientePorCedula(@Argument String cedula) {
        return clienteService.buscarPorCedula(cedula);
    }

    @QueryMapping
    public List<Porcino> porcinos() {
        return porcinoService.listarPorcinos();
    }

    @QueryMapping
    public List<Alimentacion> alimentaciones() {
        return alimentacionService.listarAlimentaciones();
    }

    @QueryMapping
    public List<Reporte> reporteClientesPorcinos() {
        return clienteService.listarClientesConPorcinos();
    }

    @MutationMapping
    public Cliente guardarCliente(@Argument("cliente") Cliente clienteInput) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteInput.getId());
        cliente.setCedula(clienteInput.getCedula());
        cliente.setNombres(clienteInput.getNombres());
        cliente.setApellidos(clienteInput.getApellidos());
        cliente.setDireccion(clienteInput.getDireccion());
        cliente.setTelefono(clienteInput.getTelefono());
        clienteService.guardarCliente(cliente);
        return cliente;
    }

    @MutationMapping
    public Boolean eliminarCliente(@Argument String cedula) {
        clienteService.eliminarCliente(cedula);
        return true;
    }

    @MutationMapping
    public Porcino guardarPorcino(@Argument("porcino") Porcino porcinoInput) {
        Porcino porcino = new Porcino();
        porcino.setId(porcinoInput.getId());
        porcino.setIdentificacion(porcinoInput.getIdentificacion());
        porcino.setRaza(porcinoInput.getRaza());
        porcino.setEdad(porcinoInput.getEdad());
        porcino.setPeso(porcinoInput.getPeso());
        porcino.setClienteId(porcinoInput.getClienteId());
        porcino.setAlimentacionId(porcinoInput.getAlimentacionId());
        porcinoService.guardarPorcino(porcino);
        return porcino;
    }

    @MutationMapping
    public Boolean eliminarPorcino(@Argument Integer id) {
        porcinoService.eliminarPorcino(id);
        return true;
    }

    @MutationMapping
    public Alimentacion guardarAlimentacion(@Argument("alimentacion") Alimentacion alimentacionInput) {
        Alimentacion alimentacion = new Alimentacion();
        alimentacion.setId(alimentacionInput.getId());
        alimentacion.setDescripcion(alimentacionInput.getDescripcion());
        alimentacion.setDosis(alimentacionInput.getDosis());
        alimentacionService.guardarAlimentacion(alimentacion);
        return alimentacion;
    }
}
