package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceResponseDTO {
    private PriceResponse priceResponse;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class PriceResponse {
        private int price;
    }
}