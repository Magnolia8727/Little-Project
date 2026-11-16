package com.example.MyLittleProject.controller;

import com.example.MyLittleProject.dto.request.SubjectRequest;
import com.example.MyLittleProject.dto.response.SubjectResponse;
import com.example.MyLittleProject.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/subjects")
@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/{courseId}")
    public SubjectResponse save (@Valid @RequestBody SubjectRequest subjectRequest,@PathVariable Integer courseId)
    {
        return subjectService.save(subjectRequest, courseId);
    }

    @GetMapping
    public List<SubjectResponse> findAll()
    {
        return subjectService.findAll();
    }
    @GetMapping("/{subjectId}")
    public SubjectResponse findById(@PathVariable Integer subjectId)
    {
        return subjectService.findById(subjectId);
    }
    @GetMapping("/subjectcode/{subjectCode}")
    public SubjectResponse findByCode(@PathVariable String subjectCode)
    {
        return subjectService.findBySubjectCode(subjectCode);
    }

    @GetMapping("/coursecode/{courseId}")
    public List<SubjectResponse>  findByCourseId(@PathVariable Integer courseId)
    {
        return subjectService.findByCourseId(courseId);
    }

    @PutMapping("/{subjectId}")
    public SubjectResponse update(@RequestBody SubjectRequest subjectRequest, @PathVariable Integer subjectId)
    {
        return subjectService.update(subjectId, subjectRequest);
    }

    @DeleteMapping("/{subjectId}")
    public void deleteById(@PathVariable Integer subjectId)
    {
        subjectService.deleteById(subjectId);
    }

}
