package com.example.MyLittleProject.services;

import com.example.MyLittleProject.dto.request.GradeRequest;
import com.example.MyLittleProject.dto.response.GradeResponse;
import com.example.MyLittleProject.exception.DuplicateResourceException;
import com.example.MyLittleProject.exception.ResourceNotFoundException;
import com.example.MyLittleProject.mapper.GradeMapper;
import com.example.MyLittleProject.models.AppUser;
import com.example.MyLittleProject.models.Grade;
import com.example.MyLittleProject.models.Student;
import com.example.MyLittleProject.models.Subject;
import com.example.MyLittleProject.repository.AppUserRepository;
import com.example.MyLittleProject.repository.GradeRepository;
import com.example.MyLittleProject.repository.StudentRepository;
import com.example.MyLittleProject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AppUserRepository appUserRepository;

    private Character calculateLetterScore(Double score)
    {
        if (score >= 80) return 'A';
        if(score >= 70) return 'B';
        if(score >= 60) return 'C';
        if(score >= 50) return 'D';
        return 'F';
    }

    public GradeResponse save (GradeRequest request)
    {
        Student student = studentRepository.findById(request.getStudentNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        if (gradeRepository.existsByStudentAndSubject(student, subject)) {
            throw new DuplicateResourceException("Grade for Student and Subject already exists");
        }
        Character letterScore = calculateLetterScore(request.getScore());
        Grade grade = gradeMapper.toEntity(request,student,subject,letterScore);

        return gradeMapper.toResponse(gradeRepository.save(grade));
    }

    public List<GradeResponse> findAll()
    {
        return gradeRepository.findAll().stream().map(gradeMapper::toResponse).toList();
    }

    public List<GradeResponse> findByStudentNumber (Long studentNumber)
    {
        Student student = studentRepository.findById(studentNumber).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        List<GradeResponse> responses = gradeRepository.findByStudent(student).stream().map(gradeMapper::toResponse).toList();
        return responses;
    }

    public List<GradeResponse> findBySubjectId (Integer subjectId)
    {
        Subject subject =  subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        return gradeRepository.findBySubject(subject).stream().map(gradeMapper::toResponse).toList();
    }

    public List<GradeResponse> getMyGrades ()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(appUser.getStudent() == null)
        {
            throw new ResourceNotFoundException("Student not found");
        }

        return gradeRepository.findByStudent(appUser.getStudent()).stream().map(gradeMapper::toResponse).toList();
    }

    public GradeResponse updateScore (Integer gradeId, Double newScore)
    {
        Grade grade =   gradeRepository.findById(gradeId).orElseThrow(() -> new ResourceNotFoundException("Grade not found"));
        grade.setScore(newScore);
        grade.setLetterScore(calculateLetterScore(newScore));
        return gradeMapper.toResponse(gradeRepository.save(grade));
    }

    public void deleteById(Integer gradeId)
    {
        if(!gradeRepository.existsById(gradeId))
            throw new ResourceNotFoundException("Grade not found");

        gradeRepository.deleteById(gradeId);
        log.info("Delete grade with id {}", gradeId);
    }

}
