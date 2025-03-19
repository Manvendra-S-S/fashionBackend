package com.fashion.user.service;

import com.fashion.user.dto.LoginDTO;
import com.fashion.user.entity.SignUp;
import com.fashion.user.exception.UserNotFoundException;
import com.fashion.user.exception.InvalidCredentialsException;
import com.fashion.user.repository.SignUpRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final Logger logger = LogManager.getLogger(LoginService.class);

    @Autowired
    private SignUpRepository signUpRepository;

    public String loginUser(LoginDTO loginDTO) {
        logger.info("Login attempt for username: {}", loginDTO.getUsername());

        try {
            // Validate input
            if (loginDTO == null || loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
                logger.error("Invalid login details: Null values found");
                throw new IllegalArgumentException("Username or password cannot be null.");
            }

            // Find user by username
            SignUp user = signUpRepository.findByUsername(loginDTO.getUsername());
            logger.info("user::"+user);
            if (user == null) {
                logger.warn("User not found: {}", loginDTO.getUsername());
                throw new UserNotFoundException("User not found!");
            }

            // Check password
            if (!user.getPassword().equals(loginDTO.getPassword())) {
                logger.warn("Invalid password attempt for username: {}", loginDTO.getUsername());
                throw new InvalidCredentialsException("Invalid credentials!");
            }

            logger.info("Login successful for username: {}", loginDTO.getUsername());
            return "Login successful! Welcome " + user.getUsername();
        } catch (UserNotFoundException | InvalidCredentialsException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: {}", e.getMessage());
            return "Invalid login details: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            return "An unexpected error occurred. Please contact support.";
        }
    }
}
