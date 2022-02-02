package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Long> {

    @Query("select t from Tasks  t where t.client.id =:id")
    public Page<Tasks> findTasksByClientId(@Param("id") long id, Pageable pageable);

    public Optional<Tasks> findTasksByTaskId(String taskId);

    public long countAllByClientId(long id);

}
