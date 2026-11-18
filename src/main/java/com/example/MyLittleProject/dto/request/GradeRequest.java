package com.example.MyLittleProject.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GradeRequest {
    @NotNull(message="Score is required")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be more than 100")
    private Double score;

    @NotNull(message="Student Number is required")
    private Long studentNumber;
    @NotNull(message="Subject ID is required")
    private Integer subjectId;
}
