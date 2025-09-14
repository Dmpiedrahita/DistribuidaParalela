package com.granja.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {
    private int id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;

}