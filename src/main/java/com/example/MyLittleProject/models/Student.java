package com.example.MyLittleProject.models;


// All abt creating a model entity, and giving it characteristics

import com.example.MyLittleProject.Gender;
import jakarta.persistence.*; // all abt database annotations that tells database that the model must be an entity
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*; // newer one unline Date, this one is mostly prefered..idk why

@Entity // from jakarta persistance jpa, to tell that this class is an entity
@NoArgsConstructor // from lombok , it create a no args constructor in background
@AllArgsConstructor // from lombok it create an all args constructor in background
@Data  // from lombok for getters and setters
// @Table(name="students") optional if I want an optional table name, otherwise table is student
public class Student {


    @Id // specifies that this is a primary key of these table
    @Column(name = "studentNumber", unique = true, nullable = false) // can alter the column give it certain characteristics
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentNumber;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required") // from validation dependecency to validate
    private String lastName;

    @Past // ensures that date is always in the past, @Future ensures that date is always in the future
    private LocalDate dateOfBirth;

    @Min(value = 5, message = "Age must be atleast 5")
    @Max(value = 30, message = "AGe must be below 30")
    private int age;

    @Enumerated(EnumType.STRING) // used if you add java enum field, if u work enums you want to work with
    private Gender gender;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @CreationTimestamp // from hibernate.annotations to create timestamp
    // @UpdateTimestamp this one is for updating
    private LocalDateTime createdDate;


    @UpdateTimestamp // used to update the updated Date
    private LocalDateTime updatedDate;


}
