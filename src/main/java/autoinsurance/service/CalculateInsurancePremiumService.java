package autoinsurance.service;

import autoinsurance.manager.CalculateInsurancePremiumManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculateInsurancePremiumService {
    private final CalculateInsurancePremiumManager manager;

    public double insurancePremium(long idRegion, long idAgeAndExperience,long idEnginePower) {
        final double coefficientTC = manager.calculateTC(idRegion);
        final double coefficientES = manager.calculateES(idAgeAndExperience);
        final double coefficientEP = manager.calculateEP(idEnginePower);
        double insurancePremiumPrice = coefficientTC * coefficientES*coefficientEP;
        final double result = Math.ceil(insurancePremiumPrice);
        return result;
    }

}