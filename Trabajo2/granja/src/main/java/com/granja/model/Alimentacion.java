package com.granja.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alimentacion {

    private Integer id;
    private String descripcion;
    private Double dosis;
}
