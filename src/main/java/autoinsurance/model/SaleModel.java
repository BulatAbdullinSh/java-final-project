package autoinsurance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleModel {
  private String insuranceCompanyName;
  private String userName;
  private String userSurname;
  private String vinNumberCar;
  private String phoneNumber;
  private String email;
  private String region;
  private String bankCardNumber;
  private int insurancePremiumPrice;
}
