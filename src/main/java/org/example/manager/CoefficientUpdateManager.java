package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.CoefficientTCUpdateResponseDTO;
import org.example.exception.CoefficientNotFoundException;
import org.example.model.CoefficientTCFullModel;
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
    }

