package com.example.MyLittleProject.models;


// All abt creating a model entity, and giving it characteristics

import com.example.MyLittleProject.Gender;
import jakarta.persistence.*; // all abt database annotations that tells database that the model must be an entity
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

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;


    private int age;

    @Enumerated(EnumType.STRING) // used if you add java enum field, if u work enums you want to work with
    private Gender gender;


    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @CreationTimestamp // from hibernate.annotations to create timestamp
    // @UpdateTimestamp this one is for updating
    private LocalDateTime createdDate;


    @UpdateTimestamp // used to update the updated Date
    private LocalDateTime updatedDate;


}
