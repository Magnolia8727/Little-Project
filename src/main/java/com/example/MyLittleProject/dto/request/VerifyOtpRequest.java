package com.example.MyLittleProject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyOtpRequest {

    @NotBlank(message = "Username is required")
    private String email;
    @NotBlank(message = "Otp is required")
    private String otp;
}
