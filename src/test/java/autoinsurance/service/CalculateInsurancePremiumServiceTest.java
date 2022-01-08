package autoinsurance.service;

import autoinsurance.manager.CalculateInsurancePremiumManager;
import autoinsurance.model.MarginLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateInsurancePremiumServiceTest {
    private CalculateInsurancePremiumManager manager;
    private final MarginLevel marginLevel;
    @Test
    void shouldCalculateInsuracePremium() {
        final CalculateInsurancePremiumService service = new CalculateInsurancePremiumService();
        final long idRegion = 10;
        final long idAgeAndExperience = 2;
        final long idEnginePower = 4;
        final long idLimitStatus = 1;
        final long idSeasonalityStatus = 8;
        final long idInsuranceCompany = 2;
        final double insuranceTerm = 1.0;
        final double marginLevel = 0.015;
        final int expected = 4411;
        final int actual = service.insurancePremium(idRegion, idAgeAndExperience, idEnginePower, idLimitStatus, idSeasonalityStatus, idInsuranceCompany, insuranceTerm,marginLevel);

        assertEquals(actual, expected);
    }
}