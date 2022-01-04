package autoinsurance.rowmapper;

import autoinsurance.model.CoefficientESModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientESRowMapper implements RowMapper<CoefficientESModel> {
    @Override
    public CoefficientESModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoefficientESModel(
                rs.getDouble("coefficient_es")
        );
    }
}
