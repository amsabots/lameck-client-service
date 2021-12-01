package com.amsabots.jenzi.client_service.controllers;


import com.amsabots.jenzi.client_service.entities.Client;
import com.amsabots.jenzi.client_service.entities.ClientSettings;
import com.amsabots.jenzi.client_service.services.ClientSettingsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-settings")
public class ClientSettingsController {
    @Autowired
    private ClientSettingsService settingsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<ClientSettings> getClientSettings(@PathVariable long id) {
        ClientSettings clientSettings = settingsService.getSettingsByClientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientSettings);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> updateClientSettings(@RequestBody ClientSettings clientSettings, @PathVariable long id) {
        clientSettings.setId(id);
        settingsService.saveDefaultSettings(clientSettings);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"The settings have been updated successfully\"}");
    }
}
