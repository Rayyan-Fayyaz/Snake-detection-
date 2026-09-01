package com.finaltime.catalogue_management.dto;

import com.finaltime.catalogue_management.entity.Snake;

public class PredictionResponse {

    private Snake snake;
    private double confidence; // 0.0 - 1.0
    private boolean stub;      // true until the real model is wired in

    public PredictionResponse() {
    }

    public PredictionResponse(Snake snake, double confidence, boolean stub) {
        this.snake = snake;
        this.confidence = confidence;
        this.stub = stub;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public boolean isStub() {
        return stub;
    }

    public void setStub(boolean stub) {
        this.stub = stub;
    }
}
