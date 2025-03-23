package com.fashion.response;

import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "wishlist_data")
public class WishlistData {

    private String userId;
    private List<Integer> prodId;
}
