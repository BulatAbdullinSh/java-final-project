package autoinsurance.manager;

import autoinsurance.dto.UploadMultipleMediaResponseDTO;
import autoinsurance.dto.UploadSingleMediaResponseDTO;
import autoinsurance.exception.UnsupportedContentTypeException;
import autoinsurance.exception.UploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class MediaManager {
    private final Path path = Path.of("media");
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
    private final Map<String, String> types = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png",
            "audio/mpeg", ".mp3"
    );

    public MediaManager() throws IOException {
        Files.createDirectories(path);
    }

    public UploadSingleMediaResponseDTO save(byte[] bytes, String contentType) {
        try {
            final String name = generateName(contentType);
            Files.write(path.resolve(name), bytes);
            return new UploadSingleMediaResponseDTO(name);
        }  catch (IOException e) {
            throw new UploadException(e);
        }
    }

    public UploadSingleMediaResponseDTO save(MultipartFile file) {
        try {
            final String name = generateName(file.getContentType());
            file.transferTo(path.resolve(name));
            return new UploadSingleMediaResponseDTO(name);
        }  catch (IOException e) {
            throw new UploadException(e);
        }
    }

    public UploadMultipleMediaResponseDTO save(List<MultipartFile> files) {
        final UploadMultipleMediaResponseDTO responseDTO = new UploadMultipleMediaResponseDTO(new ArrayList<>(files.size()));

        for (MultipartFile file : files) {
            responseDTO.getNames().add(save(file).getName());
        }

        return responseDTO;
    }

    private String generateName(String contentType) {
        return UUID.randomUUID() + getExtension(contentType);
    }

    private String getExtension(String contentType) {
        final String extension = types.get(contentType);
        if (extension == null) {
            throw new UnsupportedContentTypeException(contentType + " not allowed for upload");
        }
        return extension;
    }
}
