package com.example.MyLittleProject.repository;

import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.models.Grade;
import com.example.MyLittleProject.models.Student;
import com.example.MyLittleProject.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByStudent (Student student);
    List<Grade> findBySubject(Subject subject);
    boolean existsByStudentAndSubject(Student student, Subject subject);
}
