package com.amsabots.jenzi.client_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @author andrew mititi on Date 11/15/21
 * @Project lameck-client-service
 */

@Entity
@Table(name = "client_settings")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientSettings extends AbstractClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isProfilePhotoVisible = true;
    private boolean isSetAlertEnabled = true;
    private boolean enableLiveLocations = false;
    private float scanRadius = 10;
    private boolean hideSensitiveAccountData = false;
    private boolean useApplicationDefaultSettings = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private Client client;

}
