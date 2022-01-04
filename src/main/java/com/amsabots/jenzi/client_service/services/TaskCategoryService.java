package com.amsabots.jenzi.client_service.services;

import com.amsabots.jenzi.client_service.entities.TaskCategory;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.TaskCategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andrew mititi on Date 1/4/22
 * @Project jenzi-client-service
 */
@Service
@AllArgsConstructor
public class TaskCategoryService {
    private TaskCategoryRepo repo;

    public List<TaskCategory> findAllTasks() {
        List<TaskCategory> taskCategories = repo.findAll(Sort.by(Sort.Direction.ASC, "title"));
        if (taskCategories.isEmpty()) throw new CustomResourceNotFound("Empty data set returned");
        return taskCategories;
    }

    public TaskCategory findTaskById(long id) {
        return repo.findById(id).orElseThrow(() -> new CustomResourceNotFound("Category entry does not exists"));
    }

    public TaskCategory updateOrCreate(TaskCategory taskCategory) {
        return repo.save(taskCategory);
    }

    public void deleteEntry(long id) {
        repo.deleteById(id);
    }
}
