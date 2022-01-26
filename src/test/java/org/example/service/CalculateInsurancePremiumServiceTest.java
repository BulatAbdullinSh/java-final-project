package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;

@Testcontainers
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc

class CalculateInsurancePremiumServiceTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCalculateInsurancePremium() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/calculate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                               "idRegion": 10,
                                               "idAgeAndExperience": 1,
                                               "idEnginePower": 4,
                                               "idLimitStatus": 1,
                                               "idSeasonalityStatus": 8,
                                               "idInsuranceCompany": 2,
                                               "insuranceTerm": 1
                                             }
                                            """
                                )
                )
                .andExpectAll(
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "priceResponse": {
                                    "price": 4670
                                  }
                                }
                                """
                        )
                );
    }
}
