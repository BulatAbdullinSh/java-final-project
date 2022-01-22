package org.example.controller;

import org.example.dto.CalculateInsurancePremiumRequestDTO;
import org.example.dto.InsuranceProductSaveRequestDTO;
import org.example.service.CalculateInsurancePremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class CalculateInsurancePremiumController {
    private final CalculateInsurancePremiumService service;
    @GetMapping ("/calculate")
    public double insurancePremium(@RequestBody CalculateInsurancePremiumRequestDTO requestDTO) {
        return service.insurancePremium(requestDTO);
        }
}
