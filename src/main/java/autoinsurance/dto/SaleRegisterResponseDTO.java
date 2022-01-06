package autoinsurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleRegisterResponseDTO {
  private Sale sale;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Sale {
    private String insuranceCompanyName;
    private String userName;
    private String userSurname;
    private int insurancePremiumPrice;
  }
  }

