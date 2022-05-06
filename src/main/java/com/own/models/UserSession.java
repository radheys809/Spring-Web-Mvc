package com.own.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "auth_session")
@Data
public class UserSession {
    @Id
    private Long sessionId;
    private String sessionToken;
    private String authToken;
    private LocalTime crationTime;
    private LocalTime expiryTime;
    private boolean isExpired;
}
