package com.amsabots.jenzi.client_service.controllers;

import com.amsabots.jenzi.client_service.entities.Client;
import com.amsabots.jenzi.client_service.entities.ClientReviews;
import com.amsabots.jenzi.client_service.errorHandlers.CustomBadRequest;
import com.amsabots.jenzi.client_service.responseObjects.PageableResponse;
import com.amsabots.jenzi.client_service.services.ClientReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews-ratings")
public class ReviewsAndRatingsController {

    @Autowired
    private ClientReviewService reviewService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageableResponse<ClientReviews>> getClientReviewsByClientId(@PathVariable long id,
                                                                            @RequestParam Optional<Integer> currentPage, @RequestParam Optional<Integer> pageSize) {
        int cpage = currentPage.orElse(0);
        int page_size = pageSize.orElse(10);
        Pageable pageable = PageRequest.of(cpage, page_size);
        List<ClientReviews> clientReviews = reviewService.getReviewsByClientId(id, pageable).getContent();
        return ResponseEntity.ok(new PageableResponse<ClientReviews>(clientReviews, page_size, cpage));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientReviews> createReview(@RequestBody ClientReviews clientReviews) {
        return ResponseEntity.ok(reviewService.createOrUpdate(clientReviews));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteReview(@PathVariable long id) {
        reviewService.findReviewById(id);
        reviewService.deleteReviews(id);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"The review has been removed from the records successfully\"}");
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateReviewById(@PathVariable long id, @RequestBody ClientReviews clientReviews) {
        clientReviews.setId(id);
        reviewService.createOrUpdate(clientReviews);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"The review has been updated successfully\"}");
    }
}
