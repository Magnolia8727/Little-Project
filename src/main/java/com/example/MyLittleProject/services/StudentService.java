package com.example.MyLittleProject.services;


import com.example.MyLittleProject.Gender;
import com.example.MyLittleProject.dto.request.StudentRequest;
import com.example.MyLittleProject.dto.response.StudentResponse;
import com.example.MyLittleProject.exception.ResourceNotFoundException;
import com.example.MyLittleProject.mapper.StudentMapper;
import com.example.MyLittleProject.models.AppUser;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.repository.AppUserRepository;
import com.example.MyLittleProject.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.MyLittleProject.repository.StudentRepository;
import com.example.MyLittleProject.models.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service // tells this that this a service, holds business logic, where logic like adding or doing watever happens
@RequiredArgsConstructor // it will create my desired constructor based on my parameters
public class StudentService {

    // i dont give it a body coz it uses lombk for constructor @RequiredArgsConstructor
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final CourseRepository courseRepository;
    private final AppUserRepository appUserRepository;
    public StudentResponse save(StudentRequest request, Integer courseId) {

        if(studentRepository.existsById(request.getStudentNumber()) || studentRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException("Student already exists");

        }
        Course course = null;

        if (courseId != null) {
             course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found")  );
        }


        Student student = mapper.toEntity(request,course);
        Student savedStudent = studentRepository.save(student);
         return mapper.toResponse(savedStudent);

    }

    public StudentResponse getMyProfile()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("USer not found"));

        if (user.getStudent() == null) {
            throw new ResourceNotFoundException("No student profile linked to this account");
        }
        return mapper.toResponse(user.getStudent());
    }

    public StudentResponse updateById(Long studentNumber, StudentRequest request) {
        if (!studentRepository.existsById(studentNumber)) {
           throw  new RuntimeException("Student number not found, try signing in first");
        }

        Student currentStudent = studentRepository.findById(studentNumber).get();
        mapper.updateEntityFromRequest(currentStudent, request);
        Student updateStudent = studentRepository.save(currentStudent);
        return mapper.toResponse(updateStudent);

    }

    public StudentResponse updateByEmail(String email, StudentRequest request) {
        if (!studentRepository.existsByEmail(email)) {
            throw  new RuntimeException("email not found, try signing in first");
        }

        Student currentStudent = studentRepository.findByEmail(email).get();
        mapper.updateEntityFromRequest(currentStudent,request);
        Student updateStudent = studentRepository.save(currentStudent);

        return mapper.toResponse(updateStudent);

    }

    public List<StudentResponse> findAll(){

        return studentRepository.findAll().stream().map(mapper::toResponse).toList();

    }

    public StudentResponse findByID(Long studentNumber)
    {
        if (!studentRepository.existsById(studentNumber))
        {
            throw new RuntimeException("Student number not found, try signing in first");
        }

        StudentResponse studentResponse = mapper.toResponse(studentRepository.findById(studentNumber).get());

        return studentResponse;

    }

    public StudentResponse findByEmail(String email)
    {
        if (!studentRepository.existsByEmail(email))
        {
            throw new RuntimeException("Email not found, try signing in first");
        }

        StudentResponse studentResponse = mapper.toResponse(studentRepository.findByEmail(email).get());

        return studentResponse;
    }

    public List<StudentResponse> findByGender (Gender gender)
    {

        return studentRepository.findByGender(gender).stream().map(mapper::toResponse).toList();

    }

    public List<StudentResponse> findByAgeBetween(int min, int max)
    {
        return studentRepository.findByAgeBetween(min, max).stream().map(mapper::toResponse).toList();
    }

    public List <StudentResponse> findByDateOfBirthBetween (LocalDate date, LocalDate later)
    {
        return studentRepository.findByDateOfBirthBetween(date, later).stream().map(mapper::toResponse).toList();
    }

    public String deleteById(Long studentNumber)
    {

        if(!studentRepository.existsById(studentNumber))
        {
            throw new RuntimeException("Student number not found, try signing in first");
        }

        studentRepository.deleteById(studentNumber);
        return "Student  deleted successfully";
    }


    @Transactional //custom delete needs this, deleteById manages its own database
    public String deleteByEmail(String email)
    {

        if(!studentRepository.existsByEmail(email))
        {
            throw new RuntimeException("Email not found, try signing in first");
        }

        studentRepository.deleteByEmail(email);
        return "Student  deleted successfully";
    }

}
