package org.example.rowmapper;

import org.example.model.CoefficientEPModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CoefficientEPRowMapper implements RowMapper<CoefficientEPModel> {
    @Override
    public CoefficientEPModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientEPModel(
                rs.getDouble("coefficient_ep")
        );
    }
}
