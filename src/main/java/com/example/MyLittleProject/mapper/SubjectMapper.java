package com.example.MyLittleProject.mapper;

import com.example.MyLittleProject.dto.request.SubjectRequest;
import com.example.MyLittleProject.dto.response.SubjectResponse;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.models.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMapper {

    private final CourseMapper courseMapper;

    public Subject toEntity (SubjectRequest request, Course course)
    {
        Subject subject = new Subject();
        subject.setCourse(course);
        subject.setSubjectDescription(request.getSubjectDescription());
        subject.setSubjectName(request.getSubjectName());
        subject.setSubjectCode(request.getSubjectCode());
        return subject;
    }

    public SubjectResponse toResponse(Subject subject)
    {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectName(subject.getSubjectName());
        subjectResponse.setId(subject.getId());
        subjectResponse.setCourseName(subject.getCourse().getCourseName());
        subjectResponse.setCourseId(subject.getCourse().getId());
        subjectResponse.setUpdatedDate(subject.getUpdatedDate());
        subjectResponse.setCreatedDate(subject.getCreatedDate());
        return subjectResponse;
    }
    public void updateEntityFromRequest(Subject currentSubject, SubjectRequest request, Course course) {
        if (request.getSubjectName() != null) {
            currentSubject.setSubjectName(request.getSubjectName());
        }
        if (request.getSubjectCode() != null) {
            currentSubject.setSubjectCode(request.getSubjectCode());
        }
        if (request.getSubjectDescription() != null) {
            currentSubject.setSubjectDescription(request.getSubjectDescription());
        }
        if (course != null) {
            currentSubject.setCourse(course);
        }
    }



}
