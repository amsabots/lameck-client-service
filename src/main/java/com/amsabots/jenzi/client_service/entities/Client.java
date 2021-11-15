package com.amsabots.jenzi.client_service.entities;

import com.amsabots.jenzi.client_service.repos.ClientSettingsRepo;
import com.amsabots.jenzi.client_service.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client extends AbstractClient {

    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String userBackgroundColor;
    private boolean isActive = true;
    private boolean isVerified = false;
    private String currentLocation;
    private String secondaryPhonenumber;
    @Column(nullable = false)
    private String provider;
    @Column(unique = true, nullable = false)
    private String clientId;
    private String password;

    //    relationships
    @OneToMany(mappedBy = "client")
    private List<Tasks> tasks;
    @OneToMany(mappedBy = "client")
    private List<ClientReviews> clientReviews;
    @OneToOne(mappedBy = "client")
    private ClientSettings clientSettings;

    @PrePersist
    public void setInitialDetails() {
        setUserBackgroundColor(Utils.createRandomColor());
        if (null != clientId)
            setClientId(UUID.randomUUID().toString().replaceAll("-", ""));
    }


}
