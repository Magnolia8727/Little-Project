package com.example.MyLittleProject.controller;


import com.example.MyLittleProject.Gender;
import com.example.MyLittleProject.dto.request.StudentRequest;
import com.example.MyLittleProject.dto.response.StudentResponse;
import com.example.MyLittleProject.models.Student;
import com.example.MyLittleProject.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // tells Spring these is a cntrlleer
@RequestMapping("/api/students") // shared base url for student crud
@RequiredArgsConstructor // used constructr for student services
public class StudentController {

    /*
    *
    @PathVariable	Embedded directly inside the URL path	GET /api/students/101	Identifying one specific resource (ID, email, username)

    *
    @RequestParam	After the ? in the URL query string	GET /api/students/age?min=18&max=25	Filtering, searching, sorting, or optional values

    *
    *
    @RequestBody	Inside the hidden HTTP JSON body	POST /api/students + { "firstName": "Ana", ... }	Sending full or partial objects (creating/updating)

    *
    *  */

    private final StudentService studentService;

    // @Valid , spring will enforce those rules i made directly in the models e.g NotBlanl , @Email
    @PostMapping()
    public StudentResponse save(@Valid @RequestBody StudentRequest student, @RequestParam(required = false) Integer courseId)
    {

        return studentService.save(student,courseId);
    }


    // @Valid is not requuired in Put methods, itll want everything filled at max
    @PutMapping("/{studentNumber}")
    public StudentResponse update (@PathVariable long studentNumber, @RequestBody StudentRequest student)
    {
        return studentService.updateById(studentNumber, student);
    }

    @PutMapping("/email/{email}")
    public StudentResponse updateByEmail(@PathVariable String email, @RequestBody StudentRequest student)
    {
        return studentService.updateByEmail(email, student);
    }

    @GetMapping
    public List<StudentResponse> findAll()
    {
        return studentService.findAll();
    }

    @GetMapping("/{studentNumber}")
    public StudentResponse findById( @PathVariable long studentNumber )
    {
        return studentService.findByID(studentNumber);
    }

    @GetMapping("/email/{email}")
    public StudentResponse findByEmail(@PathVariable String email)
    {
        return studentService.findByEmail(email);
    }

    @GetMapping("/gender/{gender}")
    public List<StudentResponse> findByGender(@PathVariable Gender gender)
    {

        return studentService.findByGender(gender);
    }

    @GetMapping("/age/{min}/{max}")
    public List<StudentResponse> findByAgeBetween(@PathVariable int min, @PathVariable int max){

        return studentService.findByAgeBetween(min, max);

    }

    @DeleteMapping("/{studentNumber}")
    public void delete(@PathVariable long studentNumber)
    {
        studentService.deleteById(studentNumber);
    }


    @DeleteMapping("/email/{email}")
    public void delete(@PathVariable String email)
    {
        studentService.deleteByEmail(email);
    }

}
