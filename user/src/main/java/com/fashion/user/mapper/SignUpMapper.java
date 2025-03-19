package com.fashion.user.mapper;


import com.fashion.user.dto.SignUpDTO;
import com.fashion.user.entity.SignUp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpMapper {

    // Convert SignUpDTO to SignUp Entity
    public static SignUp toEntity(SignUpDTO dto) {
        if (dto == null) {
            return null;
        }
        return new SignUp(
                0, // ID will be auto-generated, so set it to 0 or null
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhonenumber()
        );
    }

    // Convert SignUp Entity to SignUpDTO
    public static SignUpDTO toDTO(SignUp entity) {
        if (entity == null) {
            return null;
        }
        return new SignUpDTO(
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getPhonenumber()
        );
    }

}