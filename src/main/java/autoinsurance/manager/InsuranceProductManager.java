package autoinsurance.manager;

import autoinsurance.dto.InsuranceProductGetAllResponseDTO;
import autoinsurance.dto.InsuranceProductGetByIdResponseDTO;
import autoinsurance.dto.InsuranceProductSaveRequestDTO;
import autoinsurance.dto.InsuranceProductSaveResponseDTO;
import autoinsurance.exception.InsuranceProductNotFoundException;
import autoinsurance.model.InsuranceProductModel;
import autoinsurance.rowmapper.InsuranceProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InsuranceProductManager {
    private final NamedParameterJdbcTemplate template;
    private final InsuranceProductRowMapper productRowMapper;
    private final String defaultImage = "noimage.png";
    public InsuranceProductGetAllResponseDTO getAll() {
        final List<InsuranceProductModel> items = template.query(
                // language=PostgreSQL
             """
                    SELECT id, insurance_company_name, basic_tariff, image FROM insurance_products
                    WHERE removed = FALSE
                    ORDER BY id
                    LIMIT 500
                    """,
                productRowMapper
        );

        final InsuranceProductGetAllResponseDTO responseDTO = new InsuranceProductGetAllResponseDTO(new ArrayList<>(items.size()));
        for (InsuranceProductModel item : items) {
            responseDTO.getInsuranceProducts().add(new InsuranceProductGetAllResponseDTO.InsuranceProduct(
                    item.getId(),
                    item.getInsuranceCompanyName(),
                    item.getBasicTariff(),
                    item.getImage()
            ));
        }

        return responseDTO;
    }

   public InsuranceProductGetByIdResponseDTO getById(long id) {
            try {
            final InsuranceProductModel item = template.queryForObject(
                    // language=PostgreSQL
                      """
                        SELECT id, insurance_company_name, basic_tariff, image FROM insurance_products
                        WHERE id = :id AND removed = FALSE
                        """,
                    Map.of("id", id),
                    productRowMapper
            );

            final InsuranceProductGetByIdResponseDTO responseDTO = new InsuranceProductGetByIdResponseDTO(new InsuranceProductGetByIdResponseDTO.InsuranceProduct(
                    item.getId(),
                    item.getInsuranceCompanyName(),
                    item.getBasicTariff(),
                    item.getImage()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new InsuranceProductNotFoundException(e);
        }
    }

    public InsuranceProductSaveResponseDTO save(InsuranceProductSaveRequestDTO requestDTO) {
        return requestDTO.getId() == 0 ? create(requestDTO) : update(requestDTO);
    }

    private InsuranceProductSaveResponseDTO create(InsuranceProductSaveRequestDTO requestDTO) {
        final InsuranceProductModel item = template.queryForObject(
                // language=PostgreSQL
                  """
                    INSERT INTO insurance_products (insurance_company_name, basic_tariff, image) VALUES (:insuranceCompanyName, :basicTariff, :image)
                    RETURNING id, insurance_company_name, basic_tariff, image
                    """,
                Map.of(
                        "insuranceCompanyName", requestDTO.getInsuranceCompanyName(),
                        "basicTariff", requestDTO.getBasicTariff(),
                        "image", getImage(requestDTO.getImage())
                ),
                productRowMapper
        );

        final InsuranceProductSaveResponseDTO responseDTO = new InsuranceProductSaveResponseDTO(new InsuranceProductSaveResponseDTO.InsuranceProduct(
                item.getId(),
                item.getInsuranceCompanyName(),
                item.getBasicTariff(),
                item.getImage()
        ));

        return responseDTO;
    }

    public void removeById(long id) {
        final int affected = template.update(
                // language=PostgreSQL
                """
                    UPDATE insurance_products SET removed = TRUE WHERE id = :id
                    """,
                Map.of("id", id)
        );
        if (affected == 0) {
            throw new InsuranceProductNotFoundException("product with id " + id + " not found");
        }
    }

    private InsuranceProductSaveResponseDTO update(InsuranceProductSaveRequestDTO requestDTO) {
        try {
            final InsuranceProductModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                        UPDATE insurance_products SET insurance_company_name = :insuranceCompanyName, basic_tariff = :basicTariff, image = :image
                        WHERE id = :id AND removed = FALSE
                        RETURNING id, insurance_company_name, basic_tariff, image
                        """,
                    Map.of(
                            "id", requestDTO.getId(),
                            "insuranceCompanyName", requestDTO.getInsuranceCompanyName(),
                            "basicTariff", requestDTO.getBasicTariff(),
                            "image", getImage(requestDTO.getImage())
                    ),
                    productRowMapper
            );

            final InsuranceProductSaveResponseDTO responseDTO = new InsuranceProductSaveResponseDTO(new InsuranceProductSaveResponseDTO.InsuranceProduct(
                    item.getId(),
                    item.getInsuranceCompanyName(),
                    item.getBasicTariff(),
                    item.getImage()
            ));

            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new InsuranceProductNotFoundException(e);
        }
    }

    private String getImage(String image) {
        return image == null ? defaultImage : image;
    }

}
