package com.productos.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "productos")
@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @XmlElement
    private String nombre;

    @XmlElement
    private String descripcion;

    @XmlElement
    private String categoria;

    @XmlElement
    private double valor;

    @XmlElement
    private int stock;
}
