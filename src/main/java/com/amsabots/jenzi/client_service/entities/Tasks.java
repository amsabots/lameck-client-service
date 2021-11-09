package com.amsabots.jenzi.client_service.entities;


import com.amsabots.jenzi.client_service.utils.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Tasks extends AbstractClient {

    private String title;
    private String description;
    private Date completionDate;
    @Enumerated(EnumType.STRING)
    private TaskState taskState;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
}
