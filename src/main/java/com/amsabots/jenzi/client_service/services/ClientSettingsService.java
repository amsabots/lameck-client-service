package com.amsabots.jenzi.client_service.services;

import com.amsabots.jenzi.client_service.entities.ClientSettings;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.ClientSettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andrew mititi on Date 11/15/21
 * @Project lameck-client-service
 */
@Service
public class ClientSettingsService {
    @Autowired
    private ClientSettingsRepo repo;

    public void saveDefaultSettings(ClientSettings clientSettings) {
        repo.save(clientSettings);
    }

    public ClientSettings getClientSettings(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new CustomResourceNotFound("The settings resource entry is not present"));
    }

    public void deleteSettings(long id) {
        repo.deleteById(id);
    }
}
