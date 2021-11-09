package com.amsabots.jenzi.client_service.repos;

import com.amsabots.jenzi.client_service.entities.ClientReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientReviewsRepo extends JpaRepository<ClientReviews, Long> {
    @Query("select r from ClientReviews r where r.client.id=:id")
    public Page<ClientReviews> getClientReviewsByClientId(@Param("id") long id, Pageable pageable);
}
