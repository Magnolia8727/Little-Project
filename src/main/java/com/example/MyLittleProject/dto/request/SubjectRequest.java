package com.example.MyLittleProject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequest {

    @NotBlank(message = "Subject Name is required")
    private String subjectName;
    @NotBlank(message ="Subject Code is required")
    private String subjectCode;

    private String subjectDescription;


    @Positive(message = "Course ID must be postitive")
    private Integer courseId;
}
