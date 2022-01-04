package autoinsurance.manager;

import autoinsurance.exception.CoefficientNotFoundException;
import autoinsurance.model.CoefficientEPModel;
import autoinsurance.model.CoefficientESModel;
import autoinsurance.model.CoefficientTCModel;
import autoinsurance.rowmapper.CoefficientEPRowMapper;
import autoinsurance.rowmapper.CoefficientESRowMapper;
import autoinsurance.rowmapper.CoefficientTCRowMapper;
import lombok.RequiredArgsConstructor;
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
}
