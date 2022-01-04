package com.amsabots.jenzi.client_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCategory extends AbstractClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @OneToMany(mappedBy = "taskCategory", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tasks> tasks;
}
