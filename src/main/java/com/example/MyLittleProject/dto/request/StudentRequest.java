package com.example.MyLittleProject.dto.request;

import com.example.MyLittleProject.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private Long studentNumber;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Past(message = " Date of birth must be in the past") // ensures that date is always in the past, @Future ensures that date is always in the future
    private LocalDate dateOfBirth;

    @Min(value = 5, message = "Age must be at least 5")
    @Max(value = 45 , message = "Age must be below 45")
    private Integer age;

    private Gender gender;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private Integer CourseId;
}
