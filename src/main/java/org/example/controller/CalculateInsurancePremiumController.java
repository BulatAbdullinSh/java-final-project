package org.example.controller;

import org.example.dto.CalculateInsurancePremiumRequestDTO;
import org.example.dto.PriceResponseDTO;
import org.example.exception.ServiceErrorException;
import org.example.service.CalculateInsurancePremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class CalculateInsurancePremiumController {
    private final CalculateInsurancePremiumService service;
    @GetMapping ("/calculate")
    public PriceResponseDTO priceResponse(@RequestBody CalculateInsurancePremiumRequestDTO requestDTO) throws ServiceErrorException {
        return service.priceResponse(requestDTO);
        }
}
