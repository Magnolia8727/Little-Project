package com.example.MyLittleProject.services;


import com.example.MyLittleProject.Gender;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    public Student save(Student student){

        if(studentRepository.existsById(student.getStudentNumber()) || studentRepository.existsByEmail(student.getEmail())){

            throw new RuntimeException("Student already exists");

        }

        studentRepository.save(student);
        return student;
    }


    public Student updateById(long studentNumber, Student student) {
        if (!studentRepository.existsById(studentNumber)) {
           throw  new RuntimeException("Student number not found, try signing in first");
        }

        Student currentStudent = studentRepository.findById(studentNumber).get();

        if (student.getEmail() != null)
        {
            currentStudent.setEmail(student.getEmail());
        }

        if (student.getFirstName() != null)
        {
            currentStudent.setFirstName(student.getFirstName());
        }

        if (student.getLastName() != null)
        {
            currentStudent.setLastName(student.getLastName());
        }

        if(student.getGender() != null)
        {
            currentStudent.setGender(student.getGender());
        }

        if(student.getDateOfBirth() != null)
        {
            currentStudent.setDateOfBirth(student.getDateOfBirth());
        }

        if (student.getAge() >= 5 && student.getAge() <= 30)
        {
            currentStudent.setAge(student.getAge());
        }


        return studentRepository.save(currentStudent);

    }

    public Student updateByEmail(String email, Student student) {
        if (!studentRepository.existsByEmail(email)) {
            throw  new RuntimeException("email not found, try signing in first");
        }

        Student currentStudent = studentRepository.findByEmail(email).get();

        if (student.getEmail() != null)
        {
            currentStudent.setEmail(student.getEmail());
        }

        if (student.getFirstName() != null)
        {
            currentStudent.setFirstName(student.getFirstName());
        }

        if (student.getLastName() != null)
        {
            currentStudent.setLastName(student.getLastName());
        }

        if(student.getGender() != null)
        {
            currentStudent.setGender(student.getGender());
        }

        if(student.getDateOfBirth() != null)
        {
            currentStudent.setDateOfBirth(student.getDateOfBirth());
        }

        if (student.getAge() >= 5 && student.getAge() <= 30)
        {
            currentStudent.setAge(student.getAge());
        }


        return studentRepository.save(currentStudent);

    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findByID(long studentNumber)
    {
        if (!studentRepository.existsById(studentNumber))
        {
            throw new RuntimeException("Student number not found, try signing in first");
        }

        return studentRepository.findById(studentNumber).get();

    }

    public Student findByEmail(String email)
    {
        if (!studentRepository.existsByEmail(email))
        {
            throw new RuntimeException("Email not found, try signing in first");
        }

        return studentRepository.findByEmail(email).get();

    }

    public List<Student> findByGender (Gender gender)
    {

        return studentRepository.findByGender(gender);

    }

    public List<Student> findByAgeBetween(int min, int max)
    {
        return studentRepository.findByAgeBetween(min, max);
    }

    public List <Student> findByDateOfBirthBetween (LocalDate date, LocalDate later)
    {
        return studentRepository.findByDateOfBirthBetween(date, later);
    }

    public String deleteById(long studentNumber)
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
