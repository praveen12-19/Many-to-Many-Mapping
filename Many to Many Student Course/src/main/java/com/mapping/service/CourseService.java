package com.mapping.service;


import com.mapping.dto.CourseDTO;
import com.mapping.exception.CourseNotFoundException;

public interface CourseService {
    CourseDTO findCourse(int courseId) throws CourseNotFoundException;

    CourseDTO findCourseWithStudent(int courseId) throws CourseNotFoundException;
}
