package com.granja.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer edad;
    private Double peso;

    @JsonProperty("cliente_id")
    private String clienteId;

    @JsonProperty("alimentacion_id")
    private Integer alimentacionId;
}
