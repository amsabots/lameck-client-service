package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.TaskCategory;
import com.amsabots.jenzi.client_service.services.TaskCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author andrew mititi on Date 1/4/22
 * @Project jenzi-client-service
 */

@RestController
@RequestMapping("/tasks-category")
@AllArgsConstructor
public class TasksCategoryController {
    private TaskCategoryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskCategory>> getAllTasks() {
        return ResponseEntity.ok().body(service.findAllTasks());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> createTask(@RequestBody TaskCategory taskCategory) {
        return ResponseEntity.ok().body(service.updateOrCreate(taskCategory));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody TaskCategory taskCategory,
                                                 @PathVariable long id) {
        taskCategory.setId(id);
        service.updateOrCreate(taskCategory);
        return ResponseEntity.ok().body(String.format("{\"message\":\"Category entry has been updated successfully\"}"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<TaskCategory> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.findTaskById(id));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        service.deleteEntry(id);
        return ResponseEntity.ok().body("{\"message\":\"The category entry has been deleted successfully\"}");
    }
}
