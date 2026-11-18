package com.example.MyLittleProject.controller;

import com.example.MyLittleProject.dto.request.GradeRequest;
import com.example.MyLittleProject.dto.response.GradeResponse;
import com.example.MyLittleProject.services.GradeService;
import com.example.MyLittleProject.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public GradeResponse save(@Valid @RequestBody GradeRequest gradeRequest)
    {
        return gradeService.save(gradeRequest);
    }
    @GetMapping
    public List<GradeResponse> findAll()
    {
        return gradeService.findAll();
    }
    @GetMapping("/studentNumber/{studentNumber}")
    public List<GradeResponse> findByStudentNumber(@PathVariable Long studentNumber)
    {
        return gradeService.findByStudentNumber(studentNumber);
    }
    @GetMapping("/subjectid/{subjectId}")
    public List<GradeResponse> findBySubjectId(@PathVariable Integer subjectId)
    {
        return gradeService.findBySubjectId(subjectId);
    }
    @GetMapping("/me")
    public List<GradeResponse> getMyGrades()
    {
        return gradeService.getMyGrades();
    }

    @PutMapping("/{gradeId}")
    public GradeResponse update(@PathVariable Integer gradeId, @RequestParam Double score)
    {
        return gradeService.updateScore(gradeId, score);
    }

    @DeleteMapping("/{gradeId}")
    public void delete(@PathVariable Integer gradeId)
    {
        gradeService.deleteById(gradeId);
    }
}
