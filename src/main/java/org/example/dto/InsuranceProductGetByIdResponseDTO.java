package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsuranceProductGetByIdResponseDTO {
    private InsuranceProduct insuranceProduct;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class InsuranceProduct {
        private long id;
        private String insuranceCompanyName;
        private int basicTariff;
        private String image;
    }
}
