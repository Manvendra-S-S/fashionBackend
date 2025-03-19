package com.fashion.response;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "products")
public class ProdDetails {

    @Id
    @Column(name = "productId", unique = true, nullable = false)
    private String productId;  // Ensure this is a primary key

    private String prodName;
    private int quantity;
    private float price;
    private String details;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews;
}

