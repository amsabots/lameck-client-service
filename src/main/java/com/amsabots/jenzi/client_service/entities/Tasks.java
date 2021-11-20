package com.amsabots.jenzi.client_service.entities;


import com.amsabots.jenzi.client_service.utils.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tasks_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Tasks extends AbstractClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private Date completionDate;
    @Enumerated(EnumType.STRING)
    private TaskState taskState;
    @Column(nullable = false,updatable = false)
    private String taskId;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "taskCategoryId")
    private TaskCategory taskCategory;

    @PrePersist
    public void setEntryDefaults() {
        setTaskId(UUID.randomUUID().toString().replaceAll("-", ""));
        setTaskState(TaskState.PENDING);
    }
}
