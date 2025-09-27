package com.granja.controller;

import com.granja.model.Reporte;
import com.granja.service.Interface.ClienteServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private ClienteServiceInterface clienteService;

    public ReporteController(ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String reporteClientesPorcinos(Model model) {
        List<Reporte> reporte = clienteService.listarClientesConPorcinos();
        reporte = reporte.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        model.addAttribute("reporte", reporte);
        return "reporte";
    }
}