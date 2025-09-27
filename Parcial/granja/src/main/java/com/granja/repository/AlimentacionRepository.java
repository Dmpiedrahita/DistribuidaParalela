package com.granja.repository;

import com.granja.model.Alimentacion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlimentacionRepository {

    private JdbcTemplate jdbcTemplate;

    public AlimentacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Alimentacion> rowMapper = new RowMapper<Alimentacion>() {
        @Override
        public Alimentacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Alimentacion a = new Alimentacion();
            a.setId(rs.getInt("id"));
            a.setDescripcion(rs.getString("descripcion"));
            a.setDosis(rs.getDouble("dosis"));
            return a;
        }
    };

    public List<Alimentacion> listarAlimentaciones() {
        return jdbcTemplate.query("SELECT * FROM alimentacion", rowMapper);
    }

    public void guardarAlimentacion(Alimentacion alimentacion) {
        if (alimentacion.getId() != null) {
            String sql = "UPDATE alimentacion SET descripcion = ?, dosis = ? WHERE id = ?";
            jdbcTemplate.update(sql, alimentacion.getDescripcion(), alimentacion.getDosis(), alimentacion.getId());
        } else {
            String sql = "INSERT INTO alimentacion (descripcion, dosis) VALUES (?, ?)";
            jdbcTemplate.update(sql, alimentacion.getDescripcion(), alimentacion.getDosis());
        }
    }

    public Alimentacion buscarPorId(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM alimentacion WHERE id = ?", rowMapper, id);
    }

    public void eliminarAlimentacion(int id) {
        jdbcTemplate.update("DELETE FROM alimentacion WHERE id = ?", id);
    }
}

