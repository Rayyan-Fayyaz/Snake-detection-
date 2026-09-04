package com.finaltime.catalogue_management.controller;

import com.finaltime.catalogue_management.dto.PredictionResponse;
import com.finaltime.catalogue_management.entity.Snake;
import com.finaltime.catalogue_management.service.SnakeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Tag(name = "Snake API", description = "CRUD Operations for Managing snake species records")
@RequestMapping("/api/snakes")
public class SnakeController {

    private final SnakeService service;

    @Autowired
    public SnakeController(SnakeService service) {
        this.service = service;
    }

    @Operation(summary = "Get all snakes", description = "Retrieve a list of all snake species in the database.")
    @GetMapping("/getAllSnakes")
    public List<Snake> getAllSnakes() {
        return service.findAll();
    }

    @Operation(summary = "Add a new snake", description = "Create and save a new snake species record.")
    @PostMapping("/addSnake")
    public Snake addSnake(@RequestBody Snake snake) {
        return service.save(snake);
    }

    @Operation(summary = "Update an existing snake", description = "Update the details of an existing snake species by ID.")
    @PutMapping("/updateSnake/{id}")
    public Snake updateSnake(@PathVariable Long id, @RequestBody Snake snake) {
        return service.update(id, snake);
    }

    @Operation(summary = "Delete a snake", description = "Delete a snake species record by ID.")
    @DeleteMapping("/deleteSnake/{id}")
    public void deleteSnake(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(
            summary = "Identify a snake from an uploaded photo (STUB)",
            description = "Accepts an image upload and returns species/venom info. " +
                    "Currently returns a random snake from the database as a placeholder " +
                    "until the trained CNN model is wired in — response shape will stay the same."
    )
    @PostMapping("/identify")
    public ResponseEntity<PredictionResponse> identifySnake(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PredictionResponse response = service.stubIdentify();
        if (response == null) {
            return ResponseEntity.status(503).build(); // no snakes in DB yet
        }

        return ResponseEntity.ok(response);
    }
}