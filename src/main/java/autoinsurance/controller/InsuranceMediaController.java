package autoinsurance.controller;

import autoinsurance.dto.UploadMultipleMediaResponseDTO;
import autoinsurance.manager.MediaManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class InsuranceMediaController {
    private final MediaManager manager;

    @PostMapping("/multi-multipart")
    public UploadMultipleMediaResponseDTO upload(@RequestPart List<MultipartFile> files) {
        return manager.save(files);
    }
}
