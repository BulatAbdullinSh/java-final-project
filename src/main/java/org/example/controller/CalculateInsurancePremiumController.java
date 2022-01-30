package org.example.controller;

import org.example.dto.CalculateInsurancePremiumRequestDTO;
import org.example.dto.PriceResponseDTO;
import org.example.exception.ServiceErrorException;
import org.example.service.CalculateInsurancePremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CalculateInsurancePremiumController {
    private final CalculateInsurancePremiumService service;
    @PostMapping("/calculate")
    public PriceResponseDTO priceResponse(@RequestBody CalculateInsurancePremiumRequestDTO requestDTO) throws ServiceErrorException {
        return service.priceResponse(requestDTO);
        }
}
