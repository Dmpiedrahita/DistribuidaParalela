package com.granja.controller;

import com.granja.model.Cliente;
import com.granja.service.Interface.ClienteServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceInterface clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientesListar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientesForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{cedula}")
    public String editar(@PathVariable String cedula, Model model) {
        Cliente cliente = clienteService.buscarPorCedula(cedula);
        model.addAttribute("cliente", cliente);
        return "clientesForm";
    }

    @PostMapping("/eliminar/{cedula}")
    public String eliminar(@PathVariable String cedula) {
        clienteService.eliminarCliente(cedula);
        return "redirect:/clientes";
    }
}
