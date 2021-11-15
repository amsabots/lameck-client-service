package com.amsabots.jenzi.client_service;

import com.amsabots.jenzi.client_service.entities.Client;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andrew mititi on Date 11/15/21
 * @Project lameck-client-service
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

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
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("\"statusCode\":200, \"message\":\"client id submitted has been removed successfully\"");
    }


}
