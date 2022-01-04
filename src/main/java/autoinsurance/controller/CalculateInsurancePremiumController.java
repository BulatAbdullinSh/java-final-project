package autoinsurance.controller;

import autoinsurance.service.CalculateInsurancePremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class CalculateInsurancePremiumController {
    private final CalculateInsurancePremiumService service;
    @GetMapping ("/calculate")
    public double insurancePremium(
            @RequestParam long idRegion,
            @RequestParam long idAgeAndExperience,
            @RequestParam long idEnginePower,
            @RequestParam long idLimitStatus,
            @RequestParam long idSeasonalityStatus,
            @RequestParam long idInsuranceCompany,
            @RequestParam double insuranceTerm

    ) {
        return service.insurancePremium(idRegion,idAgeAndExperience, idEnginePower,idLimitStatus,idSeasonalityStatus,idInsuranceCompany,insuranceTerm);
        }

}

