package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsuranceProductModel {
    private long id;
    private String insuranceCompanyName;
    private int basicTariff;
    private String image;
}
