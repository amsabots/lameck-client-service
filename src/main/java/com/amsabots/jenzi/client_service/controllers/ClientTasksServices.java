package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.Tasks;
import com.amsabots.jenzi.client_service.errorHandlers.CustomBadRequest;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.TaskRepo;
import com.amsabots.jenzi.client_service.responseObjects.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author andrew mititi on Date 11/19/21
 * @Project lameck-client-service
 */

@RestController
@RequestMapping("/jobs")
@Slf4j
public class ClientTasksServices {

    @Autowired
    private TaskRepo taskRepo;

    /**
     * Url preserved for admin account - security configuration to verify acccess should be pluged in
     * during spring security configuration
     */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageableResponse<Tasks>> getAllJobs(@RequestParam(required = false) Optional<Integer> page,
                                                              @RequestParam Optional<Integer> pageSize) {
        int cpage = page.orElse(0);
        int size = pageSize.orElse(15);
        Pageable pageable = PageRequest.of(cpage, size);
        return ResponseEntity
                .ok(new PageableResponse<>(taskRepo.findAll(pageable).getContent(), size, cpage));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<PageableResponse<Tasks>> getAllJobsByClientId(@RequestParam(required = false) Optional<Integer> page,
                                                                        @RequestParam Optional<Integer> pageSize,
                                                                        @PathVariable long id) {
        int cpage = page.orElse(0);
        int size = pageSize.orElse(15);
        Pageable pageable = PageRequest.of(cpage, size);
        return ResponseEntity
                .ok(new PageableResponse<>(taskRepo.findTasksByClientId(id, pageable).getContent(), size, cpage));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
        return ResponseEntity.ok(taskRepo.save(task));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tasks> updateJobRecord(@PathVariable String id, @RequestBody(required = false) Optional<Tasks> tasks) {
        tasks.orElseThrow(
                        () -> new CustomBadRequest("Upload payload conforming to the object defined must be provided in part/full"))
                .setTaskId(id);
        Tasks existingTask = taskRepo.findTasksByTaskId(id).orElseThrow(
                () -> new CustomResourceNotFound("The job id provided does not exist on our records - probably deleted")
        );
        tasks.get().setId(existingTask.getId());
        log.info("The job id selected for an update operation is {}", tasks.get().getId());
        return ResponseEntity.ok(taskRepo.save(tasks.get()));
    }

}
