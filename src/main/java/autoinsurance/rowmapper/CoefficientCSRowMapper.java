package autoinsurance.rowmapper;

import autoinsurance.model.CoefficientCSModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoefficientCSRowMapper implements RowMapper<CoefficientCSModel> {
    @Override
    public CoefficientCSModel mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new CoefficientCSModel(
                rs.getDouble("coefficient_cs")
        );
    }
}
