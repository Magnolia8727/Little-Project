package com.example.MyLittleProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private int id;
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private List<StudentResponse> students;
    private List<SubjectResponse> subjects;
}
