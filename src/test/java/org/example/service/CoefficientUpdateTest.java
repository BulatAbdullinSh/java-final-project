package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class CoefficientUpdateTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldPerformUpdateCoefficient() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/tc")
                                .queryParam("idRegion", String.valueOf(2))
                                .queryParam("coefficientTC", String.valueOf(3))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "coefficientTCUpdate": {
                                       "locality": "Горно-Алтайск",
                                       "coefficientTC": 3.0
                                     }
                                }
                                """
                        )
                );
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/es")
                                .queryParam("idAgeAndExperience", String.valueOf(2))
                                .queryParam("coefficientES", String.valueOf(3))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "coefficientESUpdate": {
                                       "ageAndExperience": "Более 22 лет со стажем вождения до 3 лет включительно",
                                       "coefficientES": 3.0
                                     }
                                }
                                """
                        )
                );
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/ep")
                                .queryParam("idEnginePower", String.valueOf(2))
                                .queryParam("coefficientEP", String.valueOf(2))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "coefficientESUpdate": {
                                       "enginePowerHorsePower": "Свыше 50 до 70 включительно",
                                       "coefficientEP": 2.0
                                     }
                                }
                                """
                        )
                );
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/cc")
                                .queryParam("idLimitStatus", String.valueOf(2))
                                .queryParam("coefficientCC", String.valueOf(3))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "coefficientCCUpdate": {
                                  "limitDrivers": "Неограниченное количество водителей",
                                  "coefficientCC": 3.0
                                    }
                                }
                                """
                        )
                );
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/cs")
                                .queryParam("idSeasonalityStatus", String.valueOf(2))
                                .queryParam("coefficientCS", String.valueOf(3))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                {
                                  "coefficientCSUpdate": {
                                       "months": "4",
                                       "coefficientCS": 3.0
                                     }
                                }
                                """
                        )
                );
    }
}