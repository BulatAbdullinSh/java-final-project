package autoinsurance.service;

import autoinsurance.manager.CalculateInsurancePremiumManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculateInsurancePremiumService {
    private final CalculateInsurancePremiumManager manager;

    public double insurancePremium(long idRegion, long idAgeAndExperience, long idEnginePower, long idLimitStatus,long idSeasonalityStatus,long idInsuranceCompany,double insuranceTerm) {
        final double coefficientTC = manager.calculateTC(idRegion);
        final double coefficientES = manager.calculateES(idAgeAndExperience);
        final double coefficientEP = manager.calculateEP(idEnginePower);
        final double coefficientCC = manager.calculateCC(idLimitStatus);
        final double coefficientCS = manager.calculateCS(idSeasonalityStatus);
        final int basicTariff = manager.calculateBasicTariff(idInsuranceCompany);
        double insurancePremiumPrice = coefficientTC * coefficientES*coefficientEP*coefficientCC*coefficientCS*basicTariff*insuranceTerm;
        final double result = Math.ceil(insurancePremiumPrice);
        return ((int) result);
    }

}