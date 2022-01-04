package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.Client;
import com.amsabots.jenzi.client_service.entities.ClientSettings;
import com.amsabots.jenzi.client_service.errorHandlers.CustomBadRequest;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.ClientSettingsRepo;
import com.amsabots.jenzi.client_service.services.ClientService;
import com.amsabots.jenzi.client_service.services.ClientSettingsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andrew mititi on Date 11/15/21
 * @Project lameck-client-service
 */
@RestController
@RequestMapping("/clients")
@Slf4j
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;
    private ClientSettingsService settingsRepo;
    private PasswordEncoder encoder;


    /*
     * The entity responsible for fetching all clients belonging to Jenzi accounts. This endpoint should only be
     * reached from an account with ADMIN privileges.
     * This should be achieved using spring boot security configuration
     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClients(@RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size) {
        int pageSize = page == null ? 20 : size;
        log.info("Received data on the request, \ncurrent page: {}\nPage size: {}", page, pageSize);
        Pageable pageable = PageRequest.of(0, pageSize);
        List<Client> client = clientService.getAllClients(pageable).getContent();
        if (client.size() < 1) throw new CustomResourceNotFound("No available clients");
        return ResponseEntity.ok(client);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("{\"statusCode\":200, \"message\":\"client id submitted has been removed successfully\"}");
    }

    /*
     * This endpoint should handle client creation, using email and clientId entries for a provider specific
     * signup otherwise an email and password should be used to handle the signup process and user creation
     * process
     * */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> initializeClient(@RequestBody Client client) {
        Client byEmail = clientService.findAccountByEmail(client.getEmail()).orElse(null);
        if (null != client.getPassword()) client.setPassword(encoder.encode(client.getPassword()));
        if (null != byEmail)
            return ResponseEntity.badRequest().body("{\"message\":\"Account with that email already exists\"}");
        Client c = clientService.createClientOrUpdate(client);
//
        return ResponseEntity.ok(c);
    }

    //update client endpoint
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody(required = false) Client client, @PathVariable String id) {
        Client e = clientService.getClientById(id);
        client.setId(e.getId());
        return ResponseEntity.ok(clientService.createClientOrUpdate(client));
    }

    //get client using email provided - primarily used to fetch account details during signup/login
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/details-email")
    public ResponseEntity<Client> findClientByEmail(@RequestBody Client c) {
        Client client = clientService.findAccountByEmail(c.getEmail()).orElseThrow(() -> new CustomResourceNotFound("Account with that specific email does not exist"));
        boolean right = encoder.matches(c.getPassword(), client.getPassword());
        if (!right) throw new CustomBadRequest("Email or password details provided are wrong");
        return ResponseEntity.ok(client);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> countClient() {
        return ResponseEntity.ok().body(String.format("{\"message\":\"%s\"}", clientService.countClients()));
    }


}
