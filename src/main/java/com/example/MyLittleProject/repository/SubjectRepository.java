package com.example.MyLittleProject.repository;

import com.example.MyLittleProject.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findByCourseId(Integer courseId);
    Subject findBySubjectCode(String subjectCode);
    Subject findBySubjectName(String subjectName);
    boolean existsBySubjectCode(String subjectCode);
    boolean existsBySubjectName(String subjectName);

}
