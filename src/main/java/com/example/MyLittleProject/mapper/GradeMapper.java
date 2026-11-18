package com.example.MyLittleProject.mapper;

import com.example.MyLittleProject.dto.request.GradeRequest;
import com.example.MyLittleProject.dto.response.GradeResponse;
import com.example.MyLittleProject.models.Grade;
import com.example.MyLittleProject.models.Student;
import com.example.MyLittleProject.models.Subject;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public Grade toEntity(GradeRequest request, Student student, Subject subject, Character letterScore)
    {
        Grade grade = new Grade();
        grade.setScore(request.getScore());
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setLetterScore(letterScore);
        return grade;
    }

    public GradeResponse toResponse(Grade grade) {
        GradeResponse response = new GradeResponse();
        response.setScore(grade.getScore());
        response.setId(grade.getId());
        response.setLetterGrade(grade.getLetterScore());
        response.setCreatedAt(grade.getCreatedAt());
        response.setUpdatedAt(grade.getUpdatedAt());
        if(grade.getStudent() != null)
        {
            response.setStudentNumber(grade.getStudent().getStudentNumber());
            response.setStudentName(grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
        }
        if(grade.getSubject() != null)
        {
            response.setSubjectId(grade.getSubject().getId());
            response.setSubjectName((grade.getSubject().getSubjectName()));
        }
        return response;
    }
}
