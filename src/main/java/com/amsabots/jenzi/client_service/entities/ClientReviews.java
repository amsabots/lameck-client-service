package com.amsabots.jenzi.client_service.entities;


import com.amsabots.jenzi.client_service.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.*;

@Entity
@Table(name = "client_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientReviews extends AbstractClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String review;
    private float stars;
    private long fundiReview;
    private String backgroundColor;
    private boolean isFlagged = false;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @PrePersist
    public void setDefaults() {
        setBackgroundColor(Utils.createRandomColor());
    }
}
