package org.example.service;

import org.example.dto.CalculateInsurancePremiumRequestDTO;
import org.example.dto.PriceResponseDTO;
import org.example.exception.ServiceErrorException;
import org.example.manager.CalculateInsurancePremiumManager;
import org.example.model.MarginLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class CalculateInsurancePremiumService {
    private final CalculateInsurancePremiumManager manager;
    private final MarginLevel marginLevel;

    public PriceResponseDTO priceResponse(CalculateInsurancePremiumRequestDTO requestDTO) throws ServiceErrorException {
        final double coefficientTC = manager.calculateTC(requestDTO.getIdRegion());
        final double coefficientES = manager.calculateES(requestDTO.getIdAgeAndExperience());
        final double coefficientEP = manager.calculateEP(requestDTO.getIdEnginePower());
        final double coefficientCC = manager.calculateCC(requestDTO.getIdLimitStatus());
        final double coefficientCS = manager.calculateCS(requestDTO.getIdSeasonalityStatus());
        final int basicTariff = manager.calculateBasicTariff(requestDTO.getIdInsuranceCompany());
        try {
            double insurancePremiumPrice = coefficientTC * coefficientES * coefficientEP * coefficientCC * coefficientCS * basicTariff * requestDTO.getInsuranceTerm() * (1 + marginLevel.getMarginLevel());

            final int result = (int) Math.ceil(insurancePremiumPrice);
            final PriceResponseDTO responseDTO = new PriceResponseDTO(new PriceResponseDTO.PriceResponse(result
            ));
            return responseDTO;
        }
        catch (Exception e) {
            throw new ServiceErrorException(e);
        }
    }
}
