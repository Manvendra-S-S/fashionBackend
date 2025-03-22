package com.fashion.serviceimpl;

import com.fashion.response.WishlistData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistServiceImpl {

    List<WishlistData> getUserWishlist(String useId, String prodId){
        List<WishlistData> wishlist = new ArrayList<>();
        WishlistData wishlistData = new WishlistData();
        wishlistData.setUserId(useId);
        wishlistData.setProductId(prodId);
        wishlistData.setQuantity(1);
        wishlistData.setPrice(100);
        wishlistData.setProdName("Product 1");
        wishlistData.setDetails("Product 1 details");

        wishlist.add(wishlistData);

        return wishlist;
    }
}
