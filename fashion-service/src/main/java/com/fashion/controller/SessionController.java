package com.fashion.controller;

import com.fashion.serviceimpl.SessionHandleServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    @Autowired
    SessionHandleServiceImpl sessionHandleService;

    @GetMapping("/saveSession")
  public ResponseEntity<?> saveSessionData(HttpSession session){
        return ResponseEntity.ok(sessionHandleService.storeSession(session));
    }
}
