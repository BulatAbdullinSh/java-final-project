package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoefficientCSUpdateResponseDTO {
    private CoefficientCSUpdate coefficientCSUpdate;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CoefficientCSUpdate {
        private String months;
        private double coefficientCS;
    }

}
