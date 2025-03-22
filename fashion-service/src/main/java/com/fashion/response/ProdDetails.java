package com.fashion.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@ToString(exclude = "reviews")
public class ProdDetails {

    @Id
    @Column(name = "productId", unique = true, nullable = false)
    private String productId;

    private String prodName;
    private int quantity;
    private float price;
    private String details;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reviews> reviews;
}

