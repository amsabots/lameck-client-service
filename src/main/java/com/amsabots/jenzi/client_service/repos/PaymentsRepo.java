package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepo extends JpaRepository<Payments, Long> {

    public List<Payments> findAllByClientId(String clientId);
}
