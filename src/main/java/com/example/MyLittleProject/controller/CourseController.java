package com.example.MyLittleProject.controller;

import com.example.MyLittleProject.dto.request.CourseRequest;
import com.example.MyLittleProject.dto.response.CourseResponse;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.services.CourseService;
import com.example.MyLittleProject.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @PostMapping
    public CourseResponse save (@Valid @RequestBody CourseRequest courseRequest)
    {
        return courseService.save(courseRequest);
    }

    @GetMapping("/code/{subjectCode}")
    public CourseResponse findBySubjectCode(@PathVariable String subjectCode)
    {
        return courseService.findByCourseCode(subjectCode);
    }
    @GetMapping("/{id}")
    public CourseResponse findById(@PathVariable Integer id)
    {
        return courseService.findById(id);
    }

    @GetMapping
    public List<CourseResponse> findAll()
    {
        return courseService.findAll();
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Integer id, @RequestBody CourseRequest courseRequest)
    {
        return courseService.update(id, courseRequest);
    }
    @PutMapping("/code/{subjectCode}")
    public CourseResponse updateByCourseCode (@PathVariable String subjectCode, @RequestBody CourseRequest courseRequest)
    {
        return courseService.updateByCourseCode(subjectCode, courseRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id)
    {
        courseService.deleteById(id);
    }

}
