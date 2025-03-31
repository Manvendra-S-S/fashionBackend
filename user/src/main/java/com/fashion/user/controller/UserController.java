package com.fashion.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fashion.user.dto.LoginDTO;
import com.fashion.user.dto.SignUpDTO;
import com.fashion.user.service.LoginService;
import com.fashion.user.service.SignUpService;
import com.fashion.user.utils.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        if (!isValidOtp(phoneNumber, otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }
        return ResponseEntity.ok("OTP verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        Map<String, String> responseMap = null;
        String status = "9";
        try {
            logger.info("Login attempt for user: {}", loginDTO.getUsername());
            String response = loginService.loginUser(loginDTO);
            if(response.startsWith("Login successful")){
                status = "1";
            }
            responseMap = new HashMap<>();
            responseMap.put("status",status);
            responseMap.put("message", response);
            Map<String, String> tokenMap = jwtUtil.generateToken(loginDTO.getUsername());
            responseMap.put("token", tokenMap.get("token"));
        } catch (Exception e) {
            responseMap.put("status",status);
            responseMap.put("message",e.getMessage());
            responseMap.put("token",null);
        }
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> register(@Valid @RequestBody SignUpDTO signUpDTO) {
        return ResponseEntity.ok(signUpService.registerUser(signUpDTO));
    }

    private Map<String, String> generateToken(LoginDTO profile) {
        Map<String, String> jmap = new HashMap<>();
        try {
            Map<String, Object> cred = new HashMap<>();
            cred.put("username", profile.getUsername());
//            cred.put("password", profile.getPassword());

            String token = Jwts.builder()
                    .setSubject(profile.getUsername())
//                    .setClaims(cred)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 2000000))
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();
            jmap.put("token", token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error generating token");
        }
        return jmap;
    }

    private boolean isValidOtp(String phoneNumber, String otp) {
        //TO-DO Simulated OTP validation logic (replace with real OTP service)
        return otp.equals("123456");
    }
}
