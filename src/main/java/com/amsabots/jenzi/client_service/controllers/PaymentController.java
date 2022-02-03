package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.Payments;
import com.amsabots.jenzi.client_service.errorHandlers.CustomResourceNotFound;
import com.amsabots.jenzi.client_service.repos.PaymentsRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Slf4j
@AllArgsConstructor
public class PaymentController {
    private PaymentsRepo repo;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payments>> paymentsByUserId(@PathVariable String id) {
        List<Payments> payments = repo.findAllByUserId(id);
        return ResponseEntity.ok(payments);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payments) {
        Payments payment = repo.save(payments);
        return ResponseEntity.ok(payment);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Payments> updatePayment(@RequestBody Payments payment, @PathVariable long id) {
        payment.setId(id);
        Payments u = repo.save(payment);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().body("{\"message\":\"The payment entry has been deleted successfully\"}");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/payment/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable long id) {
        Payments p = repo.findById(id).orElseThrow(() -> new CustomResourceNotFound("This resource cannot be traced."));
        return ResponseEntity.ok().body(p);
    }
}
