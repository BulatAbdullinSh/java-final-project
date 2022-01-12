package org.example.rowmapper;

import org.example.model.CoefficientTCModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientTCRowMapper implements RowMapper<CoefficientTCModel> {
    @Override
    public CoefficientTCModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientTCModel(
                rs.getDouble("coefficient_tc")
        );
    }
}
