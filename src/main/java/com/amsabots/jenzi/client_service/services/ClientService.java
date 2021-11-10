package com.amsabots.jenzi.client_service.services;


import com.amsabots.jenzi.client_service.entities.Client;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepo.findAll(pageable);
    }

    public Client getClientById(long id) {
        return clientRepo.findById(id).orElseThrow(() -> new CustomResourceNotFound("No client with the provided identifier"));
    }

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public void deleteClient(long id) {
        clientRepo.deleteById(id);
    }
}