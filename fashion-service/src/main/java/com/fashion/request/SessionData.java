package com.fashion.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "session_data")
@Entity
public class SessionData {
    @Id
    private String sessionId;
    private String sessionMsg;
}
