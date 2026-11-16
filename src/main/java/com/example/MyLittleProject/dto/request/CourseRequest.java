package com.example.MyLittleProject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @NotBlank(message = "Course name cant be blank")
    private String courseName;
    @NotBlank(message = "Course code cant be blank")
    private String courseCode;

    private String courseDescription;


}
