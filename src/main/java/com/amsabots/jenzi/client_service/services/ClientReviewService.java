package com.amsabots.jenzi.client_service.services;


import com.amsabots.jenzi.client_service.entities.ClientReviews;
import com.amsabots.jenzi.client_service.repos.ClientReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientReviewService {

    @Autowired
    private ClientReviewsRepo reviewsRepo;

    public List<ClientReviews> getClientReviews(long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return reviewsRepo.findAllById(ids);
    }

    public void deleteReviews(long id) {
        reviewsRepo.deleteById(id);
    }

    public void createOrUpdate(ClientReviews clientReviews) {
        reviewsRepo.save(clientReviews);
    }

    public Page<ClientReviews> getReviewsByClientId(long id, Pageable pageable) {
        return reviewsRepo.getClientReviewsByClientId(id, pageable);
    }
}
