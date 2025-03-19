package com.fashion.response;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId") // Auto-generate reviewId
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private ProdDetails product;  // Foreign key reference

    private String comments;
    private String ratings;
}

