package com.fashion.controller;

import com.fashion.request.SessionData;
import com.fashion.service.WishlistService;
import com.fashion.serviceimpl.SessionHandleServiceImpl;
import com.fashion.serviceimpl.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistController {

    @Autowired
    private WishlistServiceImpl wishlistService;

    @PostMapping("/addWishlist")
    private ResponseEntity<?> addBucket(@RequestBody SessionData sessionData){
        return ResponseEntity.ok(wishlistService.addWishlist(sessionData));
    }


    @GetMapping("/getWishlist")
    private ResponseEntity<?> getBucket(@RequestParam("userId") String sessionId){
        return ResponseEntity.ok(wishlistService.getWishlist(sessionId));
    }

    @DeleteMapping("/removeArticle")
    private ResponseEntity<?> removeArticle(@RequestParam("prodUrl") String prodUrl){
        return ResponseEntity.ok(wishlistService.removeArticle(prodUrl));
    }
}
