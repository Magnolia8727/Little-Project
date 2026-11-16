package com.example.MyLittleProject.dto.response;


import com.example.MyLittleProject.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private long studentNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int age;
    private Gender gender;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
