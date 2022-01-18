package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.exception.CoefficientNotFoundException;
import org.example.model.*;
import org.example.rowmapper.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CoefficientUpdateManager {
    private final NamedParameterJdbcTemplate template;
    public final CoefficientTCFullRowMapper coefficientTCFullRowMapper;
    public final CoefficientESFullRowMapper coefficientESFullRowMapper;
    public final CoefficientEPFullRowMapper coefficientEPFullRowMapper;
    public final CoefficientCCFullRowMapper coefficientCCFullRowMapper;
    public final CoefficientCSFullRowMapper coefficientCSFullRowMapper;

    public CoefficientTCUpdateResponseDTO updateCoefficientTC (long idRegion, double coefficientTC) {
        try {
            final CoefficientTCFullModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE region SET coefficient_tc = :coefficientTC
                        WHERE id_region = :idRegion AND removed = FALSE
                        RETURNING locality, coefficient_tc
                        """,
                    Map.of(
                            "idRegion", idRegion,
                            "coefficientTC",coefficientTC
                    ),
                    coefficientTCFullRowMapper
            );
            final CoefficientTCUpdateResponseDTO responseDTO = new CoefficientTCUpdateResponseDTO(new CoefficientTCUpdateResponseDTO.CoefficientTCUpdate(
                    item.getLocality(),
                    item.getCoefficientTC()
            ));

            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    public CoefficientESUpdateResponseDTO updateCoefficientES (long idAgeAndExperience, double coefficientES) {
        try {
            final CoefficientESFullModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE age_and_experience_status SET coefficient_es = :coefficientES
                        WHERE id_age_and_experience = :idAgeAndExperience AND removed = FALSE
                        RETURNING age_and_experience, coefficient_es
                        """,
                    Map.of(
                            "idAgeAndExperience", idAgeAndExperience,
                            "coefficientES",coefficientES
                    ),
                    coefficientESFullRowMapper
            );
            final CoefficientESUpdateResponseDTO responseDTO = new CoefficientESUpdateResponseDTO(new CoefficientESUpdateResponseDTO.CoefficientESUpdate(
                    item.getAgeAndExperience(),
                    item.getCoefficientES()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    public CoefficientEPUpdateResponseDTO updateCoefficientEP (long idEnginePower, double coefficientEP) {
        try {
            final CoefficientEPFullModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE engine_power SET coefficient_ep = :coefficientEP
                        WHERE id_engine_power = :idEnginePower AND removed = FALSE
                        RETURNING engine_power_horse_power, coefficient_ep
                        """,
                    Map.of(
                            "idEnginePower",idEnginePower,
                            "coefficientEP",coefficientEP
                    ),
                    coefficientEPFullRowMapper
            );
            final CoefficientEPUpdateResponseDTO responseDTO = new CoefficientEPUpdateResponseDTO(new CoefficientEPUpdateResponseDTO.CoefficientEPUpdate(
                    item.getEnginePowerHorsePower(),
                    item.getCoefficientEP()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    public CoefficientCCUpdateResponseDTO updateCoefficientCC (long idLimitStatus, double coefficientCC) {
        try {
            final CoefficientCCFullModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE limit_status SET coefficient_cc = :coefficientCC
                        WHERE id_limit_status = :idLimitStatus AND removed = FALSE
                        RETURNING limit_drivers, coefficient_cc
                        """,
                    Map.of(
                            "idLimitStatus",idLimitStatus,
                            "coefficientCC",coefficientCC
                    ),
                    coefficientCCFullRowMapper
            );
            final CoefficientCCUpdateResponseDTO responseDTO = new CoefficientCCUpdateResponseDTO(new CoefficientCCUpdateResponseDTO.CoefficientCCUpdate(
                    item.getLimitDrivers(),
                    item.getCoefficientCC()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    public CoefficientCSUpdateResponseDTO updateCoefficientCS (long idSeasonalityStatus, double coefficientCS) {
        try {
            final CoefficientCSFullModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE seasonality_status SET coefficient_cs = :coefficientCS
                        WHERE id_seasonality_status = :idSeasonalityStatus AND removed = FALSE
                        RETURNING months, coefficient_cs
                        """,
                    Map.of(
                            "idSeasonalityStatus",idSeasonalityStatus,
                            "coefficientCS",coefficientCS
                    ),
                    coefficientCSFullRowMapper
            );
            final CoefficientCSUpdateResponseDTO responseDTO = new CoefficientCSUpdateResponseDTO(new CoefficientCSUpdateResponseDTO.CoefficientCSUpdate(
                    item.getMonths(),
                    item.getCoefficientCS()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new CoefficientNotFoundException(e);
        }
    }

    }

