package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryRepo extends JpaRepository<TaskCategory, Long> {
}
