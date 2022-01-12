package org.example.manager;

import org.example.exception.CoefficientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.model.*;
import org.example.rowmapper.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@RequiredArgsConstructor

public class CalculateInsurancePremiumManager {
   private final NamedParameterJdbcTemplate template;
   public final CoefficientTCRowMapper coefficientTCRowMapper;
   public final CoefficientESRowMapper coefficientESRowMapper;
   public final CoefficientEPRowMapper coefficientEPRowMapper;
   public final CoefficientCCRowMapper coefficientCCRowMapper;
   public final CoefficientCSRowMapper coefficientCSRowMapper;
    private final BasicTariffRowMapper basicTariffRowMapper;


    public double calculateTC(long idRegion) {
        try {
            final CoefficientTCModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT coefficient_tc FROM region
                      WHERE id_region=:idRegion AND removed = FALSE                 
                      """,
                    Map.of("idRegion", idRegion),
                    coefficientTCRowMapper
            );
            return item.getCoefficientTC();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    public double calculateES(long idAgeAndExperience) {
        try {
            final CoefficientESModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT coefficient_es FROM age_and_experience_status
                      WHERE id_age_and_experience=:idAgeAndExperience AND removed = FALSE                  
                      """,
                    Map.of("idAgeAndExperience", idAgeAndExperience),
                    coefficientESRowMapper
            );
            return item.getCoefficientES();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }
    public double calculateEP(long idEnginePower) {
        try {
            final CoefficientEPModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT coefficient_ep FROM engine_power
                      WHERE id_engine_power=:idEnginePower AND removed = FALSE                  
                      """,
                    Map.of("idEnginePower", idEnginePower),
                    coefficientEPRowMapper
            );
            return item.getCoefficientEP();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }
    public double calculateCC(long idLimitStatus) {
        try {
            final CoefficientCCModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT coefficient_cc FROM limit_status
                      WHERE id_limit_status=:idLimitStatus AND removed = FALSE                  
                      """,
                    Map.of("idLimitStatus", idLimitStatus),
                    coefficientCCRowMapper
            );
            return item.getCoefficientCC();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }
    public double calculateCS(long idSeasonalityStatus) {
        try {
            final CoefficientCSModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT coefficient_cs FROM seasonality_status
                      WHERE id_seasonality_status=:idSeasonalityStatus AND removed = FALSE                  
                      """,
                    Map.of("idSeasonalityStatus", idSeasonalityStatus),
                    coefficientCSRowMapper
            );
            return item.getCoefficientCS();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }
    public int calculateBasicTariff(long idInsuranceCompany) {
        try {
            final BasicTariffModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                      SELECT basic_tariff FROM insurance_products
                      WHERE id=:idInsuranceCompany AND removed = FALSE                  
                      """,
                    Map.of("idInsuranceCompany", idInsuranceCompany),
                    basicTariffRowMapper
            );
            return item.getBasicTariff();
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }
}
