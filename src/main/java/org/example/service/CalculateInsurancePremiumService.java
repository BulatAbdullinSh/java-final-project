package org.example.service;

import org.example.dto.CalculateInsurancePremiumRequestDTO;
import org.example.manager.CalculateInsurancePremiumManager;
import org.example.model.MarginLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CalculateInsurancePremiumService {
    private final CalculateInsurancePremiumManager manager;
    private final MarginLevel marginLevel;

    public double insurancePremium(CalculateInsurancePremiumRequestDTO requestDTO) {
        final double coefficientTC = manager.calculateTC(requestDTO.getIdRegion());
        final double coefficientES = manager.calculateES(requestDTO.getIdAgeAndExperience());
        final double coefficientEP = manager.calculateEP(requestDTO.getIdEnginePower());
        final double coefficientCC = manager.calculateCC(requestDTO.getIdLimitStatus());
        final double coefficientCS = manager.calculateCS(requestDTO.getIdSeasonalityStatus());
        final int basicTariff = manager.calculateBasicTariff(requestDTO.getIdInsuranceCompany());
        double insurancePremiumPrice = coefficientTC * coefficientES * coefficientEP * coefficientCC * coefficientCS * basicTariff * requestDTO.getInsuranceTerm() * (1 + marginLevel.getMarginLevel());
        final double result = Math.ceil(insurancePremiumPrice);
        return ((int) result);
    }

}
