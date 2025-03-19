package com.fashion.response;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate reviewId
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private ProdDetails product;  // Foreign key reference

    private String comments;
    private String ratings;
}

