package com.fashion.payment.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orderdto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid pincode format")
    private String pincode;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "OTP cannot be blank")
    private String otp; // This will be verified before order confirmation
}

