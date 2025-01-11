package com.spritecloud.demo.Controller;

import com.spritecloud.demo.Service.InferenceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/infer")
public class InferenceController {

    private final InferenceService inferenceService;

    public InferenceController(InferenceService inferenceService) {
        this.inferenceService = inferenceService;
    }

    @PostMapping
    public ResponseEntity<byte[]> infer(@RequestParam("file") MultipartFile file) {
        try {
            // Get the image bytes from the uploaded file
            byte[] imageBytes = file.getBytes();

            // Process the image using your inference service
            byte[] processedImage = inferenceService.inferImage(imageBytes);

            // Return the processed image as binary data
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Adjust to the image type (e.g., PNG/JPEG)
            return new ResponseEntity<>(processedImage, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error: " + e.getMessage()).getBytes());
        }
    }
}
