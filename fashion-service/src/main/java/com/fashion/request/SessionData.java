package com.fashion.request;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "session_data")
@Entity
public class SessionData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sid;
    private String sessionId;
    private String sessionMsg;
    private String productUrl;
    private String prodName;
    private int quantity;
    private float price;
}
