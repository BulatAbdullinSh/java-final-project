package autoinsurance.rowmapper;

import autoinsurance.model.InsuranceProductModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InsuranceProductRowMapper implements RowMapper<InsuranceProductModel> {
    @Override
    public InsuranceProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new InsuranceProductModel(
                rs.getLong("id"),
                rs.getString("insurance_company_name"),
                rs.getInt("basic_tariff"),
                rs.getString("image")
        );
    }

}
