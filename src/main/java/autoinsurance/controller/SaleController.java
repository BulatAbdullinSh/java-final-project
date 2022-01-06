package autoinsurance.controller;

import autoinsurance.dto.SaleRegisterRequestDTO;
import autoinsurance.dto.SaleRegisterResponseDTO;
import autoinsurance.manager.SaleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
  private final SaleManager manager;

  @PostMapping("/register")
  public SaleRegisterResponseDTO register(@RequestBody SaleRegisterRequestDTO requestDTO) {
    return manager.register(requestDTO);
  }
}
