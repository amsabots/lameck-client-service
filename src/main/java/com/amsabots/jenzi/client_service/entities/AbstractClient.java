package com.amsabots.jenzi.client_service.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractClient implements Serializable {

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;


}
