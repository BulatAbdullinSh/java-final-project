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

class SaleRegisterTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCalculateInsurancePremium() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/sales/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {  
                                            "insuranceCompanyName": "Insurance Company №3",
                                            "userName": "Jonn",
                                            "userSurname": "Smitt",
                                            "vinNumberCar": "ZC45545NT4544",
                                            "phoneNumber": "9503432343",
                                            "email": "smitt@mail.ru",
                                            "region":  "Казань",
                                            "bankCardNumber": "2344 3456 6789 3456",
                                            "insurancePremiumPrice": 5340
                                                }
                                            """
                                )
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "sale": {
                                     "insuranceCompanyName": "Insurance Company №3",
                                     "userName": "Jonn",
                                     "userSurname": "Smitt",
                                     "insurancePremiumPrice": 5340
                                   }
                                 }
                                """
                        )
                );
    }
}
