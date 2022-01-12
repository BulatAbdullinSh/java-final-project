package org.example.controller;

import org.example.dto.InsuranceProductGetAllResponseDTO;
import org.example.dto.InsuranceProductGetByIdResponseDTO;
import org.example.dto.InsuranceProductSaveRequestDTO;
import org.example.dto.InsuranceProductSaveResponseDTO;
import org.example.manager.InsuranceProductManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insuranceproducts")
@RequiredArgsConstructor
public class InsuranceProductController {
    private final InsuranceProductManager manager;

    @GetMapping("/getAll")
    public InsuranceProductGetAllResponseDTO getAll() {

        return manager.getAll();
    }
    @GetMapping("/getById")
    public InsuranceProductGetByIdResponseDTO getByIdFromParam(@RequestParam long id) {

        return manager.getById(id);
    }
    @GetMapping("/getById/{id}")
    public InsuranceProductGetByIdResponseDTO getByIdFromPath(@PathVariable long id) {

        return manager.getById(id);
    }

    @PostMapping("/save")
    public InsuranceProductSaveResponseDTO save(@RequestBody InsuranceProductSaveRequestDTO requestDTO) {
        return manager.save(requestDTO);
    }

    @PostMapping("/removeById")
    public void removeByIdFromParam(@RequestParam long id) {
        manager.removeById(id);
    }

    @PostMapping("/removeById/{id}")
    public void removeByIdFromPath(@PathVariable long id) {
        manager.removeById(id);
    }
}
