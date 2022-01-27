package org.example.rowmapper;

import org.example.model.CoefficientCSFullModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientCSFullRowMapper implements RowMapper<CoefficientCSFullModel> {
    @Override
    public CoefficientCSFullModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientCSFullModel(
                rs.getString("months"),
                rs.getDouble("coefficient_cs")
        );
    }
}
