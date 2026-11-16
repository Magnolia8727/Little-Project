package com.example.MyLittleProject.mapper;

import com.example.MyLittleProject.dto.request.CourseRequest;
import com.example.MyLittleProject.dto.response.CourseResponse;
import com.example.MyLittleProject.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request)
    {
        Course course = new Course();
        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setCourseDescription(request.getCourseDescription());

        return course;
    }


    public CourseResponse toResponse (Course course)
    {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCourseCode(course.getCourseCode());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setCourseDescription(course.getCourseDescription());
        courseResponse.setId(course.getId());

        return courseResponse;
    }

    public void updateEntityFromRequest(Course course, CourseRequest request)
    {
        if(request.getCourseCode() != null)
        {
            course.setCourseCode(request.getCourseCode());
        }

        if(request.getCourseName() != null)
        {
            course.setCourseName(request.getCourseName());
        }

        if(request.getCourseDescription() != null)
        {
            course.setCourseDescription(request.getCourseDescription());
        }

    }

}
