package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoefficientTCUpdateResponseDTO {
    private CoefficientTCUpdate coefficientTCUpdate;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CoefficientTCUpdate {
        private String locality;
        private double coefficientTC;
    }

}
