package org.example.rowmapper;

import org.example.model.BasicTariffModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BasicTariffRowMapper implements RowMapper<BasicTariffModel> {
    @Override
    public BasicTariffModel mapRow(ResultSet rs, int rowNum) throws SQLException {
      return  new BasicTariffModel(
                rs.getInt("basic_tariff")
        );
    }
}
