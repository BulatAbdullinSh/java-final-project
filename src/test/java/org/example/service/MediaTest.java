package org.example.service;

import org.hamcrest.core.StringEndsWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
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
class MediaTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldUploadBytes() throws Exception {
        final byte[] content = TestUtils.bytesFromResources("media.png");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/media/bytes")
                                .contentType(MediaType.IMAGE_PNG_VALUE)
                                .content(content)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.name").value(StringEndsWith.endsWith(".png"))
                );
    }

    @Test
    void shouldUploadSingleMultipart() throws Exception {
        final MockMultipartFile file = new MockMultipartFile("file", "media.png", MediaType.IMAGE_PNG_VALUE, TestUtils.inputStreamFromResources("media.png"));

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/media/multipart")
                                .file(file)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.name").value(StringEndsWith.endsWith(".png"))
                );
    }

    @Test
    void shouldUploadMultipleMultipart() throws Exception {
        final MockMultipartFile image = new MockMultipartFile("files", "media.png", MediaType.IMAGE_PNG_VALUE, TestUtils.inputStreamFromResources("media.png"));

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/media/multi-multipart")
                                .file(image)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.names.length()").value(1)
                );
    }
}
