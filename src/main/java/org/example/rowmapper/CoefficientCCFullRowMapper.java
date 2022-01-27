package org.example.rowmapper;

import org.example.model.CoefficientCCFullModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientCCFullRowMapper implements RowMapper<CoefficientCCFullModel> {
    @Override
    public CoefficientCCFullModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientCCFullModel(
                rs.getString("limit_drivers"),
                rs.getDouble("coefficient_cc")
        );
    }
}
