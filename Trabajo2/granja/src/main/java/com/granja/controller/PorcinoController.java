package com.granja.controller;

import com.granja.model.Cliente;
import com.granja.model.Porcino;
import com.granja.service.Impl.PorcinoService;
import com.granja.service.Interface.AlimentacionServiceInterface;
import com.granja.service.Interface.ClienteServiceInterface;
import com.granja.service.Interface.PorcinoServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/porcinos")
public class PorcinoController {

    private final PorcinoServiceInterface porcinoService;
    private final AlimentacionServiceInterface alimentacionService;
    private final ClienteServiceInterface clienteService;

    public PorcinoController(PorcinoService porcinoService, AlimentacionServiceInterface alimentacionService, ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
        this.alimentacionService = alimentacionService;
        this.porcinoService = porcinoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("porcinos", porcinoService.listarPorcinos());
        return "porcinosListar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("porcino", new Porcino());
        model.addAttribute("alimentaciones", alimentacionService.listarAlimentaciones());
        return "porcinosForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Porcino porcino) {
        Cliente c = clienteService.buscarPorCedula(String.valueOf(porcino.getClienteId()));
        porcino.setClienteId(c.getCedula());
        porcinoService.guardarPorcino(porcino);
        return "redirect:/porcinos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("porcino", porcinoService.buscarPorId(id));
        model.addAttribute("alimentaciones", alimentacionService.listarAlimentaciones());
        return "porcinosForm";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        porcinoService.eliminarPorcino(id);
        return "redirect:/porcinos";
    }
}