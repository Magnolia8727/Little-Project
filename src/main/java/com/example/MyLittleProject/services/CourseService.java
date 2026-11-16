package com.example.MyLittleProject.services;


import com.example.MyLittleProject.dto.request.CourseRequest;
import com.example.MyLittleProject.dto.response.CourseResponse;
import com.example.MyLittleProject.mapper.CourseMapper;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResponse save (CourseRequest course){

        if(courseRepository.existsByCourseCode(course.getCourseCode())){
            throw new RuntimeException("Course code already exists");
        }

        Course newCourse = courseMapper.toEntity(course);
        Course savedCourse = courseRepository.save(newCourse);

        return courseMapper.toResponse(savedCourse);
    }

    public CourseResponse findByCourseCode (String courseCode){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new RuntimeException("Course doesnt exists");
        }

        Course course = courseRepository.findByCourseCode(courseCode);
        return courseMapper.toResponse(course);

    }

    public List<CourseResponse> findAll(){
       return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
    }

    public CourseResponse findById(Integer id){

        if (!courseRepository.existsById(id)){
            throw new RuntimeException("Course doesnt exists");
        }

        return courseMapper.toResponse(courseRepository.findById(id).get());
    }

    public CourseResponse findByCourseName(String courseName)
    {
        if(!courseRepository.existsByCourseName(courseName))
        {
            throw new RuntimeException("Course doesnt exists");
        }

        return courseMapper.toResponse(courseRepository.findByCourseName(courseName));


    }

    public boolean existsByCourseCode (String courseCode){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new RuntimeException("Course code doesnt exists");
        }
        return courseRepository.existsByCourseCode(courseCode);
    }

    public boolean existsByCourseName(String courseName)
    {
        if(!courseRepository.existsByCourseName(courseName))
        {
            throw new RuntimeException("Course name doesnt exists");
        }

        return courseRepository.existsByCourseName(courseName);
    }

    public CourseResponse update(Integer id, CourseRequest courseRequest){

        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course doesnt exists");
        }
        Course savedCourse = courseRepository.findById(id).get();
        courseMapper.updateEntityFromRequest(savedCourse, courseRequest);
        Course savedCourse2 = courseRepository.save(savedCourse);
        return courseMapper.toResponse(savedCourse2);

    }

    public CourseResponse updateByCourseCode(String courseCode,CourseRequest courseRequest){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new RuntimeException("Course doesnt exists");
        }

        Course savedCourse = courseRepository.findByCourseCode(courseCode);
        courseMapper.updateEntityFromRequest(savedCourse, courseRequest);
        Course savedCourse2 = courseRepository.save(savedCourse);
        return courseMapper.toResponse(savedCourse2);
    }

    public void deleteById(Integer id){
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course doesnt exists");
        }
        courseRepository.deleteById(id);
        log.info("Course deleted successfully");

    }

    @Transactional
    public void deleteByCourseCode(String courseCode){
        if(!courseRepository.existsByCourseCode(courseCode)){
            throw new RuntimeException("Course doesnt exists");

        }
        courseRepository.deleteByCourseCode(courseCode);
        log.info("Course deleted successfully");
    }








}
