package autoinsurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculateCoefficientESRequestDTO {
    private CalculateCoefficientES calculateCoefficientES;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CalculateCoefficientES {
        private double coefficientES;
    }
}
