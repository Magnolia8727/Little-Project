package com.example.MyLittleProject.controller;


import com.example.MyLittleProject.Gender;
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
    public Student save(@Valid @RequestBody Student student)
    {

        return studentService.save(student);
    }


    // @Valid is not requuired in Put methods, itll want everything filled at max
    @PutMapping("/{studentNumber}")
    public Student update (@PathVariable long studentNumber, @RequestBody Student student)
    {
        return studentService.updateById(studentNumber, student);
    }

    @PutMapping("/email/{email}")
    public Student updateByEmail(@PathVariable String email, @RequestBody Student student)
    {
        return studentService.updateByEmail(email, student);
    }

    @GetMapping
    public List<Student> findAll()
    {
        return studentService.findAll();
    }

    @GetMapping("/{studentNumber}")
    public Student findById( @PathVariable long studentNumber )
    {
        return studentService.findByID(studentNumber);
    }

    @GetMapping("/email/{email}")
    public Student findByEmail(@PathVariable String email)
    {
        return studentService.findByEmail(email);
    }

    @GetMapping("/gender/{gender}")
    public List<Student> findByGender(@PathVariable Gender gender)
    {

        return studentService.findByGender(gender);
    }

    @GetMapping("/age/{min}/{max}")
    public List<Student> findByAgeBetween(@PathVariable int min, @PathVariable int max){

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
