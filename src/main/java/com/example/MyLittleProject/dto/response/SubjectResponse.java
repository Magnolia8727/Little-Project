package com.example.MyLittleProject.dto.response;

import com.example.MyLittleProject.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {

    private int id;
    private String subjectName;
    private String subjectCode;

    //Course
    private Integer courseId;
    private String courseName;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
