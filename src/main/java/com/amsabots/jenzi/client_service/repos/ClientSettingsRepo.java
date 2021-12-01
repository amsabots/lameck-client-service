package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.ClientSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author andrew mititi on Date 11/15/21
 * @Project lameck-client-service
 */
public interface ClientSettingsRepo extends JpaRepository<ClientSettings, Long> {

    public Optional<ClientSettings> findClientSettingsByClientId(long id);
}
