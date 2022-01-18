package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoefficientCCUpdateResponseDTO {
    private CoefficientCCUpdate coefficientCCUpdate;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CoefficientCCUpdate {
        private String limitDrivers;
        private double coefficientCC;
    }

}
