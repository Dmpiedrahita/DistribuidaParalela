package com.granja.repository;

import com.granja.model.Porcino;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PorcinoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PorcinoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Porcino> viewMapper = (rs, rowNum) -> {
        Porcino v = new Porcino();
        v.setId(rs.getInt("id"));
        v.setIdentificacion(rs.getString("identificacion"));
        v.setRaza(rs.getInt("raza"));
        v.setEdad(rs.getInt("edad"));
        v.setPeso(rs.getDouble("peso"));
        v.setClienteId(rs.getString("cliente_id"));
        v.setAlimentacionId(rs.getInt("alimentacion_id"));
        return v;
    };

    public List<Porcino> findAll() {
        String sql = "SELECT * from porcinos ORDER BY id DESC";
        return jdbcTemplate.query(sql, viewMapper);
    }

    public Porcino findById(int id) {
        String sql = "SELECT * FROM porcinos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rn) -> {
            Porcino p = new Porcino();
            p.setId(rs.getInt("id"));
            p.setIdentificacion(rs.getString("identificacion"));
            p.setRaza(rs.getInt("raza"));
            p.setEdad(rs.getInt("edad"));
            p.setPeso(rs.getDouble("peso"));
            p.setClienteId(rs.getString("cliente_id"));
            p.setAlimentacionId(rs.getInt("alimentacion_id"));
            return p;
        }, id);
    }

    public void insert(Porcino p) {
        String sql = "INSERT INTO porcinos (identificacion, raza, edad, peso, cliente_id, alimentacion_id) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                p.getIdentificacion(), p.getRaza(), p.getEdad(), p.getPeso(),
                p.getClienteId(), p.getAlimentacionId());
    }

    public void update(Porcino p) {
        String sql = "UPDATE porcinos SET identificacion = ?, raza = ?, edad = ?, peso = ?, cliente_id = ?, alimentacion_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                p.getIdentificacion(), p.getRaza(), p.getEdad(), p.getPeso(),
                p.getClienteId(), p.getAlimentacionId(), p.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM porcinos WHERE id = ?", id);
    }
}
