package autoinsurance.rowmapper;

import autoinsurance.model.SaleModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SaleRowMapper implements RowMapper<SaleModel> {
    @Override
    public SaleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SaleModel(
                rs.getString("insurance_company_name"),
                rs.getString("user_name"),
                rs.getString("user_surname"),
                rs.getString("vin_number_car"),
                rs.getString("phone_number"),
                rs.getString("email"),
                rs.getString("region"),
                rs.getString("bank_card_number"),
                rs.getInt("insurance_premium_price")
        );
    }
}
