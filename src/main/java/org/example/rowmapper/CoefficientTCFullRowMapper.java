package org.example.rowmapper;

import org.example.model.CoefficientTCFullModel;
import org.example.model.CoefficientTCModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientTCFullRowMapper implements RowMapper<CoefficientTCFullModel> {
    @Override
    public CoefficientTCFullModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientTCFullModel(
                rs.getString("locality"),
                rs.getDouble("coefficient_tc")
        );
    }
}
