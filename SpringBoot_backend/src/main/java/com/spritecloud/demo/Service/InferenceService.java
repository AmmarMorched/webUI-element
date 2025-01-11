package com.spritecloud.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class InferenceService {

    private static final Logger logger = Logger.getLogger(InferenceService.class.getName());

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate bean

    public byte[] inferImage(byte[] imageBytes) throws Exception {
        // Log the start of the inference process
        logger.info("Starting inference process...");

        // Call your FastAPI endpoint with the image bytes and process the response
        byte[] processedImage = callFastApiEndpoint(imageBytes);

        // Return the processed image
        return processedImage;
    }

    private byte[] callFastApiEndpoint(byte[] imageBytes) throws IOException {
        try {
            // Set up the headers for the multipart request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Create the body of the request
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(imageBytes) {
                @Override
                public String getFilename() {
                    return "image.png"; // The name of the file being uploaded
                }
            });

            // Create the request entity with headers and body
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Make the POST request to the FastAPI endpoint
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "http://localhost:8000/infer",
                    HttpMethod.POST,
                    requestEntity,
                    byte[].class
            );

            // Check if the response is successful
            if (response.getStatusCode().is2xxSuccessful()) {
                // Log the success
                logger.info("Inference successful, received processed image.");
                return response.getBody(); // Return the processed image as byte array
            } else {
                // Log the error status code
                logger.severe("Error calling FastAPI: " + response.getStatusCode());
                throw new RuntimeException("Error calling FastAPI: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // Log the error and throw a runtime exception
            logger.severe("Error during image inference: " + e.getMessage());
            throw new RuntimeException("Error during image inference", e);
        }
    }
}
