package com.mapping.service;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.exception.CourseNotFoundException;
import com.mapping.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentDTO studentDTO);

    StudentDTO findStudent(int studentId) throws StudentNotFoundException;

    StudentDTO findStudentWithCourse(int studentId) throws StudentNotFoundException;

    void updateCourseDetails(int studentId, int courseId, String courseName) throws StudentNotFoundException, CourseNotFoundException;

    void deleteStudent(int studentId) throws StudentNotFoundException;

    void addCourse(int studentId, CourseDTO courseDTO) throws StudentNotFoundException;

    void removeCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException;

    void addCoStudent(int courseId, StudentDTO studentDTO) throws CourseNotFoundException;

    List<StudentDTO> findAllStudents();
}
