package com.example.MyLittleProject.repository;

import com.example.MyLittleProject.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseName(String courseName);
    Course findByCourseCode (String courseCode);
    boolean existsByCourseCode (String courseCode);
    boolean existsByCourseName (String courseName);
    void deleteByCourseCode (String courseCode);

}
