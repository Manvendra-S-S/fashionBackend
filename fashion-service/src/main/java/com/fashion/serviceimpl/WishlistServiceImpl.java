package com.fashion.serviceimpl;

import com.fashion.repo.SessionRepo;
import com.fashion.request.SessionData;
import com.fashion.response.WishlistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistServiceImpl {

    @Autowired
    private SessionRepo sessionRepo;

    public String addWishlist(SessionData data){
        List<SessionData> wishlist = new ArrayList<>();
        sessionRepo.save(data);
        return "Successfully Added to cart!!";
    }

    public List<SessionData> getWishlist(String sessionId) {
        return sessionRepo.getWishListData(sessionId);
    }

    public String removeArticle(String prodUrl){
         sessionRepo.deleteByProductUrl(prodUrl);
        return "Deleted Successfully !!";
    }

}
