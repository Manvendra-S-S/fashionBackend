package com.fashion.user.service;

import com.fashion.user.dto.SignUpDTO;
import com.fashion.user.entity.SignUp;
import com.fashion.user.mapper.SignUpMapper;
import com.fashion.user.repository.SignUpRepository;
import com.fashion.user.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private SignUpMapper signUpMapper;

    @Autowired
    private SignUpRepository signUpRepository;

    public String registerUser(SignUpDTO signUpDTO) {
        try {
            // Check for null or invalid inputs
            if (signUpDTO == null || signUpDTO.getUsername() == null || signUpDTO.getPhonenumber() == null) {
                throw new IllegalArgumentException("Invalid signup details provided.");
            }

            // Check if username or phone number already exists
            if (signUpRepository.existsByUsername(signUpDTO.getUsername())) {
                throw new UserAlreadyExistsException("Username is already taken!");
            }

            if (signUpRepository.existsByPhonenumber(signUpDTO.getPhonenumber())) {
                throw new UserAlreadyExistsException("Phone number is already registered!");
            }

            // Save user in database
            SignUp user = SignUpMapper.toEntity(signUpDTO);
            signUpRepository.save(user);
            return "Congratulations! You are registered successfully.";

        } catch (UserAlreadyExistsException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return "Invalid user details: " + e.getMessage();
        } catch (DataIntegrityViolationException e) {
            return "Database error: Unable to register user. Please try again later.";
        } catch (Exception e) {
            return "An unexpected error occurred. Please contact support.";
        }
    }
}
