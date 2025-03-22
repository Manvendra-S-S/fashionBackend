package com.fashion.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@Data
@ToString(exclude = "product")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonBackReference
    private ProdDetails product;

    private String comments;
    private String ratings;
}

