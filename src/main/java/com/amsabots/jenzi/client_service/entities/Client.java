package com.amsabots.jenzi.client_service.entities;

import com.amsabots.jenzi.client_service.enumUtils.AccountType;
import com.amsabots.jenzi.client_service.enumUtils.ClientAccountProvider;
import com.amsabots.jenzi.client_service.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client extends AbstractClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private boolean resetPassword = false;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private ClientAccountProvider provider;
    @Enumerated
    private AccountType accountType = AccountType.FREE;

    @Column(unique = true)
    private String clientId;
    private String password;
    private float accountBalance = 0;

    //    relationships
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tasks> tasks;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<ClientReviews> clientReviews;
    @OneToOne(mappedBy = "client", orphanRemoval = true)
    private ClientSettings clientSettings;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payments> payments;

    @PrePersist
    public void setInitialDetails() {
        setUserBackgroundColor(Utils.createRandomColor());
        if (null == clientId) {
            setClientId(UUID.randomUUID().toString().replaceAll("-", ""));
            setProvider(ClientAccountProvider.CUSTOM);
        }
    }


}
