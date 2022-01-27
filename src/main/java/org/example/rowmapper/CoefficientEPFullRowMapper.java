package org.example.rowmapper;

import org.example.model.CoefficientEPFullModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientEPFullRowMapper implements RowMapper<CoefficientEPFullModel> {
    @Override
    public CoefficientEPFullModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientEPFullModel(
                rs.getString("engine_power_horse_power"),
                rs.getDouble("coefficient_ep")
        );
    }
}
