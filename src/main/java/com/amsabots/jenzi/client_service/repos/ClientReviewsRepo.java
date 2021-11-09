package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.ClientReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientReviewsRepo extends JpaRepository<ClientReviews, Long> {
}
