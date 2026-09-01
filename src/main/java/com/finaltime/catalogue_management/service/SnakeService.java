package com.finaltime.catalogue_management.service;

import com.finaltime.catalogue_management.dto.PredictionResponse;
import com.finaltime.catalogue_management.entity.Snake;
import com.finaltime.catalogue_management.repository.SnakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SnakeService {

    private final SnakeRepository repository;
    private final Random random = new Random();

    @Autowired
    public SnakeService(SnakeRepository repository) {
        this.repository = repository;
    }

    /**
     * STUB — picks a random snake already in the database and fakes a
     * confidence score. This is a placeholder for the real CNN inference
     * call, which will eventually replace this method's internals without
     * changing its signature or the frontend contract.
     */
    public PredictionResponse stubIdentify() {
        List<Snake> allSnakes = repository.findAll();
        if (allSnakes.isEmpty()) {
            return null;
        }

        Snake picked = allSnakes.get(random.nextInt(allSnakes.size()));
        double fakeConfidence = 0.70 + (random.nextDouble() * 0.25); // 70-95%

        return new PredictionResponse(picked, fakeConfidence, true);
    }

    public List<Snake> findAll() {
        return repository.findAll();
    }

    public Snake save(Snake snake) {
        return repository.save(snake);
    }

    public Snake update(Long id, Snake snake) {
        snake.setId(id);
        return repository.save(snake);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
