package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
