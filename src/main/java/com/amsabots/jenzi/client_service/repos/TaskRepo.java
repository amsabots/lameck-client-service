package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("select t from Tasks  t where t.client.clientId =:id")
    public Page<List<Tasks>> findTasksByClientId(@Param("id") long id, Pageable pageable);
}
