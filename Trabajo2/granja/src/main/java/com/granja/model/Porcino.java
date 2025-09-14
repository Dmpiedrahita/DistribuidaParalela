package com.granja.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Porcino {
    private Integer id;
    private String identificacion;
    private Integer raza;
    private Integer eedad;
    private Double peso;
    private Integer clienteId;
    private Integer alimentacionId;
}
