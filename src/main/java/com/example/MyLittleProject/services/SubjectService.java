package com.example.MyLittleProject.services;

import com.example.MyLittleProject.dto.request.SubjectRequest;
import com.example.MyLittleProject.dto.response.SubjectResponse;
import com.example.MyLittleProject.mapper.SubjectMapper;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.models.Subject;
import com.example.MyLittleProject.repository.CourseRepository;
import com.example.MyLittleProject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final CourseRepository courseRepository;

    public SubjectResponse save(SubjectRequest subjectRequest, Integer courseId) {

        if (subjectRepository.existsBySubjectCode(subjectRequest.getSubjectCode())) {
            throw new RuntimeException("Subject already exists");
        }
        Course course = null;
        if (courseId != null) {
            course = courseRepository.findById(courseId).get();
        }


        Subject subject = subjectMapper.toEntity(subjectRequest, course);
        subjectRepository.save(subject);
        return subjectMapper.toResponse(subject);
    }

    public SubjectResponse findById(Integer id) {

        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }

        return subjectMapper.toResponse(subjectRepository.findById(id).get());
    }

    public SubjectResponse findBySubjectCode(String subjectCode) {

        if (!subjectRepository.existsBySubjectCode(subjectCode)) {
            throw new RuntimeException("Subject not found");
        }

        return subjectMapper.toResponse(subjectRepository.findBySubjectCode(subjectCode));

    }

    public List<SubjectResponse> findAll() {
        return subjectRepository.findAll().stream().map(subjectMapper::toResponse).toList();
    }

    public List<SubjectResponse> findByCourseId(Integer id) {

        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }

        return subjectRepository.findByCourseId(id).stream().map(subjectMapper::toResponse).toList();
    }

    public SubjectResponse update(Integer subId , SubjectRequest subjectRequest) {
        if (!subjectRepository.existsById(subId))
        {
            throw new RuntimeException("Subject not found");
        }
        Subject savedSubject = subjectRepository.findById(subId).get();
        Course course = null;
            if(subjectRequest.getCourseId() != null)
            {
                course =  courseRepository.findById(subjectRequest.getCourseId()).get();
            }
        subjectMapper.updateEntityFromRequest(savedSubject, subjectRequest,course);

        return subjectMapper.toResponse(subjectRepository.save(savedSubject));
    }

    public void deleteById(Integer id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }

        subjectRepository.deleteById(id);
        log.info("Subject deleted successfully");
    }
}