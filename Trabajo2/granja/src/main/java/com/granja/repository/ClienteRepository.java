package com.granja.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.granja.model.Cliente;
import com.granja.model.Porcino;
import com.granja.model.Reporte;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cliente> rowMapper = (rs, rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setCedula(rs.getString("cedula"));
        cliente.setNombres(rs.getString("nombres"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setTelefono(rs.getString("telefono"));
        return cliente;
    };

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM clientes ORDER BY cedula DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void save(Cliente cliente) {
        String sql = "INSERT INTO clientes (cedula, nombres, apellidos, direccion, telefono) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (cedula) DO UPDATE SET " +
                "nombres = EXCLUDED.nombres, apellidos = EXCLUDED.apellidos, " +
                "direccion = EXCLUDED.direccion, telefono = EXCLUDED.telefono";
        jdbcTemplate.update(sql,
                cliente.getCedula(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono());
    }

    public Cliente findByCedula(String cedula) {
        String sql = "SELECT * FROM clientes WHERE cedula = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, cedula);
    }

    public void deleteByCedula(String cedula) {
        String sql = "DELETE FROM clientes WHERE cedula = ?";
        System.out.println("Ejecutando SQL: " + sql.replace("?", "'" + cedula + "'"));
        jdbcTemplate.update(sql, cedula);
    }

    public List<Reporte> reporte() {
        String sql = """
        SELECT c.cedula, c.nombres, c.apellidos,
               json_agg(json_build_object(
                   'id', p.id,
                   'identificacion', p.identificacion,
                   'raza', p.raza,
                   'edad', p.edad,
                   'peso', p.peso,
                   'cliente_id', p.cliente_id,
                   'alimentacion_id', p.alimentacion_id
               )) AS porcinos
        FROM clientes c
        LEFT JOIN porcinos p ON c.cedula = p.cliente_id
        GROUP BY c.cedula, c.nombres, c.apellidos
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Reporte reporte = new Reporte();
            reporte.setCedula(rs.getString("cedula"));
            reporte.setNombres(rs.getString("nombres"));
            reporte.setApellidos(rs.getString("apellidos"));

            String porcinosJson = rs.getString("porcinos");

            List<Porcino> porcinos;
            if (porcinosJson == null) {
                porcinos = new ArrayList<>();
            } else {
                try {
                    porcinos = new ObjectMapper().readValue(porcinosJson,
                            new TypeReference<List<Porcino>>() {});
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error parsing JSON de porcinos", e);
                }
            }

            reporte.setPorcinos(porcinos);
            return reporte;
        });
    }

}