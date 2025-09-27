package com.granja.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    private String cedula;
    private String nombres;
    private String apellidos;
    private List<Porcino> porcinos = new ArrayList<>();
}

