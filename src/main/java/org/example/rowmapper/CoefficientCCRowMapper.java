package org.example.rowmapper;

import org.example.model.CoefficientCCModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientCCRowMapper implements RowMapper<CoefficientCCModel> {
    @Override
    public CoefficientCCModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientCCModel(
                rs.getDouble("coefficient_cc")
        );
    }
}
