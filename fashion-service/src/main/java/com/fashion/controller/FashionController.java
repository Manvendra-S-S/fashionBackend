package com.fashion.controller;

import com.fashion.serviceimpl.AllPhotoServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FashionController {

    @Autowired
    private AllPhotoServiceImpl allPhotoService;

    @GetMapping("/allPhoto")
    public ResponseEntity<?> getAllPhoto(){

        List<String> fileNames = allPhotoService.listFiles();
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping("products")
    public ResponseEntity<?> getImageDetail(@RequestParam("product") String prodId) {

        return ResponseEntity.ok(allPhotoService.getProdDetails(prodId));
    }



}
