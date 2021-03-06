package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findClientByClientId(String id);

    Optional<Client> findClientByEmail(String email);
}
