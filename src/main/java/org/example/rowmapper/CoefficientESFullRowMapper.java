package org.example.rowmapper;

import org.example.model.CoefficientESFullModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientESFullRowMapper implements RowMapper<CoefficientESFullModel> {
    @Override
    public CoefficientESFullModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientESFullModel(
                rs.getString("age_and_experience"),
                rs.getDouble("coefficient_es")
        );
    }
}
