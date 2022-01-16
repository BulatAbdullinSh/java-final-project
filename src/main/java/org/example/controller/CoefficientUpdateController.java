package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CoefficientTCUpdateResponseDTO;
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
/*
    @PostMapping("/es")
    @PostMapping("/ep")
    @PostMapping("/cc")
    @PostMapping("/cs")
*/

}
