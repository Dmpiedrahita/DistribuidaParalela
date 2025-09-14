package com.granja.controller;

import com.granja.model.Alimentacion;
import com.granja.service.Interface.AlimentacionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
@RequestMapping("/alimentaciones")
public class AlimentacionController {

    private final AlimentacionServiceInterface alimentacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alimentaciones", alimentacionService.listarAlimentaciones());
        return "alimentacionesListar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alimentacion", new Alimentacion());
        return "alimentacionesForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Alimentacion alimentacion) {
        alimentacionService.guardarAlimentacion(alimentacion);
        return "redirect:/alimentaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("alimentacion", alimentacionService.buscarPorId(id));
        return "alimentacionesForm";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        alimentacionService.eliminarAlimentacion(id);
        return "redirect:/alimentaciones";
    }
}
