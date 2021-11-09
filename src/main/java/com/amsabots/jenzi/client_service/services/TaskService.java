package com.amsabots.jenzi.client_service.services;


import com.amsabots.jenzi.client_service.entities.Tasks;
import com.amsabots.jenzi.client_service.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Page<Tasks> getAllTaskByClientId(long id, Pageable pageable) {
        return taskRepo.findTasksByClientId(id, pageable);
    }

    public Tasks createTask(Tasks tasks) {
        return taskRepo.save(tasks);
    }

    public void deleteTasks(long id) {
        taskRepo.deleteById(id);
    }
}
