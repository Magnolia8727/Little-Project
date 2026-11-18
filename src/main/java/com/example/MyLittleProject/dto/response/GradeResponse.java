package com.example.MyLittleProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponse {
    private Integer id;
    private Double score;
    private Character letterGrade;
    private Long studentNumber;
    private String studentName;
    private Integer subjectId;
    private String subjectName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
