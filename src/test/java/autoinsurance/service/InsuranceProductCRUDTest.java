package autoinsurance.service;

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
class InsuranceProductCRUDTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldPerformProductCRUD() throws Exception {
        // getAll
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/insuranceproducts/getAll")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                    {
                                      "insuranceProducts": [
                                              {
                                                "id": 1,
                                                "insuranceCompanyName": "Insurance Company №1",
                                                "basicTariff": 3750,
                                                "image": "ic1.png"
                                              },
                                              {
                                                "id": 2,
                                                "insuranceCompanyName": "Insurance Company №2",
                                                "basicTariff": 3550,
                                                "image": "ic2.png"
                                              },
                                              {
                                                "id": 3,
                                                "insuranceCompanyName": "Insurance Company №3",
                                                "basicTariff": 3500,
                                                "image": "ic3.png"
                                              },
                                              {
                                                "id": 4,
                                                "insuranceCompanyName": "Insurance Company №4",
                                                "basicTariff": 4100,
                                                "image": "ic4.png"
                                              },
                                              {
                                                "id": 5,
                                                "insuranceCompanyName": "Insurance Company №5",
                                                "basicTariff": 4200,
                                                "image": "ic5.png"
                                              },
                                              {
                                                "id": 6,
                                                "insuranceCompanyName": "Insurance Company №6",
                                                "basicTariff": 4300,
                                                "image": "ic6.png"
                                              }
                                            ]
                                    }
                                    """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/insuranceproducts/getById")
                                .queryParam("id", String.valueOf(3))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                    {
                                      "insuranceProducts": {
                                                "id": 3,
                                                "insuranceCompanyName": "Insurance Company №3",
                                                "basicTariff": 3500,
                                                "image": "ic3.png"
                                              }
                                    }
                                    """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/insuranceproducts/getById/{id}", 4)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                    {
                                      "insuranceProducts": {
                                                                 "id": 4,
                                                                 "insuranceCompanyName": "Insurance Company №4",
                                                                 "basicTariff": 4100,
                                                                 "image": "ic4.png"
                                                               }
                                    }
                                    """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/insuranceproducts/getById")
                                .queryParam("id", String.valueOf(999))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isNotFound()
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/insuranceproducts/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        // language=JSON
                                        """
                                            {
                                                "id": 0,
                                                "insuranceCompanyName": "Insurance company №8",
                                                "basicTariff": 4150
                                              
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
                                  "insuranceProducts": {
                                    "id": 7,
                                    "insuranceCompanyName": "Insurance company №8",
                                    "basicTariff": 4150,
                                    "image": "noimage.png"
                                  }
                                }
                                """
                        )
                );
    }
}