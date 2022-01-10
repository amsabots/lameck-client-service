package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.Tasks;
import com.amsabots.jenzi.client_service.errorHandlers.CustomBadRequest;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.TaskRepo;
import com.amsabots.jenzi.client_service.responseObjects.PageableResponse;
import com.amsabots.jenzi.client_service.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Locale;
import java.util.Optional;


/**
 * @author andrew mititi on Date 11/19/21
 * @Project lameck-client-service
 */

@RestController
@RequestMapping("/jobs")
@Slf4j
public class ClientTasksController {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TaskService service;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/owner/{id}")
    public ResponseEntity<PageableResponse<Tasks>> getAllJobsByClientId(@RequestParam(required = false) Optional<Integer> page,
                                                                        @RequestParam Optional<Integer> pageSize,
                                                                        @PathVariable long id) {
        int cpage = page.orElse(0);
        int size = pageSize.orElse(15);
        Pageable pageable = PageRequest.of(cpage, size);
        return ResponseEntity
                .ok(new PageableResponse<>(taskRepo.findTasksByClientId(id, pageable).getContent(), size, cpage));
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Tasks> getJobById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findJobTaskById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
        return ResponseEntity.ok(service.createTask(task));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tasks> updateJobRecord(@PathVariable long id, @RequestBody(required = false)
            Optional<Tasks> tasks) {
        tasks.orElseThrow(
                () -> new CustomBadRequest("Upload payload conforming to the object defined must be provided in part/full"));
        Tasks existingTask = service.findJobTaskById(String.valueOf(id));
        tasks.get().setId(existingTask.getId());
        return ResponseEntity.ok(service.createTask(tasks.get()));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTaskEntry(@PathVariable long id) {
        service.findJobTaskById(String.valueOf(id));
        service.deleteTasks(id);
        return ResponseEntity.ok().body("{\"message\":\"The entry has been permanently deleted from the system\"}");
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaskCount(@RequestParam Optional<String> count_type, @RequestParam Optional<Long> id) {
        Long u_id = id.orElse(null);
        if (null == u_id) return ResponseEntity.ok().body(String.format("{\"message\":\"%s\"}", taskRepo.count()));
        return ResponseEntity.ok().body(String.format("{\"message\":\"%s\"}", taskRepo.countAllByClientId(u_id)));
    }

}
