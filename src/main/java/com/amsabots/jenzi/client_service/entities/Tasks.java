package com.amsabots.jenzi.client_service.entities;


import com.amsabots.jenzi.client_service.enumUtils.TaskState;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tasks extends AbstractClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private Date completionDate;
    @Enumerated(EnumType.STRING)
    private TaskState.TaskStateEnum taskState;
    @Column(nullable = false, updatable = false)
    private String taskId;
    private String fundiId;
    @Enumerated(EnumType.STRING)
    private TaskState.PendingTaskStates pendingTaskStates;
    private String actionReason;

    @ManyToOne
    @JoinColumn(name = "clientId", updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "taskCategoryId")
    private TaskCategory taskCategory;

    @PrePersist
    public void setEntryDefaults() {
        setTaskId(UUID.randomUUID().toString().replaceAll("-", ""));
        setTaskState(TaskState.TaskStateEnum.ONGOING);
        setPendingTaskStates(TaskState.PendingTaskStates.ONGOING);
    }
}
