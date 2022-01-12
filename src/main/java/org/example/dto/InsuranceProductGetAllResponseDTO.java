package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsuranceProductGetAllResponseDTO {
    private List<InsuranceProduct> insuranceProducts;

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
