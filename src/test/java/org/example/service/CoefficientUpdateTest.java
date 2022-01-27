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
/*        mockMvc.perform(
                        MockMvcRequestBuilders.post("/coefficient/es")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                               "idAgeAndExperience": 2,
                                               "coefficientES": 3
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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                               "idEnginePower": 2,
                                               "coefficientEP": 2
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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                               "idLimitStatus": 2,
                                               "coefficientCC": 3
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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                               "idSeasonalityStatus": 2,
                                               "coefficientCS": 3
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
                                  "coefficientCSUpdate": {
                                       "months": "4",
                                       "coefficientCS": 3.0
                                     }
                                }
                                """
                        )
                );*/
    }
}