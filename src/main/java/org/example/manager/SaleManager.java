package org.example.manager;

import org.example.dto.SaleRegisterRequestDTO;
import org.example.dto.SaleRegisterResponseDTO;
import org.example.exception.InsuranceProductNotFoundException;
import org.example.model.SaleModel;
import org.example.rowmapper.SaleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SaleManager {
  private final NamedParameterJdbcTemplate template;
  private final SaleRowMapper saleRowMapper;

  @Transactional
  public SaleRegisterResponseDTO register(SaleRegisterRequestDTO requestDTO) {
    try {
      final SaleModel sale = template.queryForObject(
          // language=PostgreSQL
          """
              INSERT INTO sales_insurance_products (insurance_company_name, user_name, user_surname,vin_number_car,phone_number,email,region,bank_card_number,insurance_premium_price) VALUES (:insuranceCompanyName, :userName, :userSurname, :vinNumberCar,:phoneNumber,:email,:region,:bankCardNumber,:insurancePremiumPrice)
              RETURNING insurance_company_name, user_name, user_surname,vin_number_car,phone_number,email,region,bank_card_number,insurance_premium_price
              """,
          Map.of(
                  "insuranceCompanyName", requestDTO.getInsuranceCompanyName(),
                  "userName", requestDTO.getUserName(),
                  "userSurname", requestDTO.getUserSurname(),
                  "vinNumberCar", requestDTO.getVinNumberCar(),
                  "phoneNumber", requestDTO.getPhoneNumber(),
                  "email", requestDTO.getEmail(),
                  "region", requestDTO.getRegion(),
                  "bankCardNumber", requestDTO.getBankCardNumber(),
                  "insurancePremiumPrice", requestDTO.getInsurancePremiumPrice()
          ),
          saleRowMapper
      );

      return new SaleRegisterResponseDTO(new SaleRegisterResponseDTO.Sale(
              sale.getInsuranceCompanyName(),
              sale.getUserName(),
              sale.getUserSurname(),
              sale.getInsurancePremiumPrice()
      ));

    } catch (EmptyResultDataAccessException e) {
      throw new InsuranceProductNotFoundException(e);
    }
  }
}
