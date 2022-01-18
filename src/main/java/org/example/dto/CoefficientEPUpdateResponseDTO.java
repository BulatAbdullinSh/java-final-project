package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoefficientEPUpdateResponseDTO {
    private CoefficientEPUpdate coefficientESUpdate;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CoefficientEPUpdate {
        private String enginePowerHorsePower;
        private double coefficientEP;
    }

}
