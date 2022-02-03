package com.amsabots.jenzi.client_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="client_payments")
@Getter
@Setter
public class Payments extends AbstractClient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fundiId;
    private String userId;
    private double amount;
    private boolean disputed = false;
    private String projectId;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
}
