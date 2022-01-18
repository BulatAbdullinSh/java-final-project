package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoefficientESUpdateResponseDTO {
    private CoefficientESUpdate coefficientESUpdate;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CoefficientESUpdate {
        private String ageAndExperience;
        private double coefficientES;
    }

}
