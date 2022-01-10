package com.amsabots.jenzi.client_service.services;


import com.amsabots.jenzi.client_service.entities.Tasks;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Tasks createTask(Tasks tasks) {
        return taskRepo.save(tasks);
    }

    public void deleteTasks(long id) {
        taskRepo.deleteById(id);
    }

    public Tasks findJobTaskById(String id) {
        if (id.length() > 6) return taskRepo.findTasksByTaskId(id)
                .orElseThrow(() -> new CustomResourceNotFound("The Job id provided does not match any existing records"));
        return taskRepo.findById(Long.valueOf(id))
                .orElseThrow(() -> new CustomResourceNotFound("The Job id provided does not match any existing records"));
    }
}
