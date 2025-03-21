package com.fashion.response;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "wishlist_data")
public class WishlistData {

    private String userId;
    private String productId;
    private int quantity;
    private float price;
    private String prodName;
    private String details;
}
