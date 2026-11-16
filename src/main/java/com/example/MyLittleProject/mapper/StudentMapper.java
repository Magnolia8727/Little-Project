package com.example.MyLittleProject.mapper;

import com.example.MyLittleProject.dto.request.StudentRequest;
import com.example.MyLittleProject.dto.response.StudentResponse;
import com.example.MyLittleProject.models.Course;
import com.example.MyLittleProject.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final CourseMapper courseMapper;
    //DTO to Entity
    public Student toEntity(StudentRequest request, Course course)
    {
        if (request == null) return null;
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setStudentNumber(request.getStudentNumber());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setCourse(course);
        return student;
    }

    //Entity to resonse
    public StudentResponse toResponse(Student student)
    {
        if (student == null) return null;
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setStudentNumber(student.getStudentNumber());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setAge(student.getAge());
        studentResponse.setGender(student.getGender());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        studentResponse.setCreatedDate(student.getCreatedDate());
        studentResponse.setUpdatedDate(student.getUpdatedDate());
        return studentResponse;
    }

    public void updateEntityFromRequest(Student currentStudent, StudentRequest request)
    {
        if (currentStudent == null) return;
        if (request == null) return;

        if (request.getEmail() != null) {
            currentStudent.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) {
            currentStudent.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            currentStudent.setLastName(request.getLastName());
        }
        if (request.getGender() != null) {
            currentStudent.setGender(request.getGender());
        }
        if (request.getDateOfBirth() != null) {
            currentStudent.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getAge() >= 5 && request.getAge() <= 30) {
            currentStudent.setAge(request.getAge());
        }


    }

}
