package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.manager.CoefficientUpdateManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coefficient")
@RequiredArgsConstructor
public class CoefficientUpdateController {
    private final CoefficientUpdateManager manager;

    @PostMapping("/tc")
    public CoefficientTCUpdateResponseDTO updateCoefficientTC(@RequestParam long idRegion, @RequestParam double coefficientTC) {
        return manager.updateCoefficientTC(idRegion, coefficientTC);
    }

    @PostMapping("/es")
    public CoefficientESUpdateResponseDTO updateCoefficientES(@RequestParam long idAgeAndExperience, @RequestParam double coefficientES) {
        return manager.updateCoefficientES(idAgeAndExperience, coefficientES);
    }

    @PostMapping("/ep")
    public CoefficientEPUpdateResponseDTO updateCoefficientEP(@RequestParam long idEnginePower, @RequestParam double coefficientEP) {
        return manager.updateCoefficientEP(idEnginePower, coefficientEP);
    }

    @PostMapping("/cc")
    public CoefficientCCUpdateResponseDTO updateCoefficientCC(@RequestParam long idLimitStatus, @RequestParam double coefficientCC) {
        return manager.updateCoefficientCC(idLimitStatus, coefficientCC);
    }

    @PostMapping("/cs")
    public CoefficientCSUpdateResponseDTO updateCoefficientCS(@RequestParam long idSeasonalityStatus, @RequestParam double coefficientCS) {
        return manager.updateCoefficientCS(idSeasonalityStatus, coefficientCS);
    }
}
