package com.spritecloud.demo.Service;

public class InferenceResult {
    private String predictions; // JSON string
    private byte[] annotatedImage; // Image bytes

    public InferenceResult(String predictions, byte[] annotatedImage) {
        this.predictions = predictions;
        this.annotatedImage = annotatedImage;
    }

    public String getPredictions() {
        return predictions;
    }

    public byte[] getAnnotatedImage() {
        return annotatedImage;
    }
}
