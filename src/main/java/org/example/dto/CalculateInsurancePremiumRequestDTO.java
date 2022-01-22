package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculateInsurancePremiumRequestDTO {
    private long idRegion;
    private long idAgeAndExperience;
    private long idEnginePower;
    private long idLimitStatus;
    private long idSeasonalityStatus;
    private long idInsuranceCompany;
    private double insuranceTerm;
}
