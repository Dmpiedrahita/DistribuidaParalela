package com.granja.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Raza {
    YORK(1, "york"),
    HAMP(2, "hamp"),
    DUROC(3, "duroc");

    private final Integer id;
    private final String raza;

    public static Raza fromId(Integer id) {
        for (Raza r : Raza.values()) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Raza no encontrada para el id: " + id);
    }

    public static Raza fromRaza(String raza) {
        for (Raza r : Raza.values()) {
            if (r.getRaza().equalsIgnoreCase(raza)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Raza no encontrada para el nombre: " + raza);
    }
}