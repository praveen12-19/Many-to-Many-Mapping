package com.mapping.repository;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.entity.Course;
import com.mapping.entity.Student;
import com.mapping.exception.CourseNotFoundException;
import com.mapping.exception.StudentNotFoundException;

import java.util.List;

public interface StudentRepository {
    void saveStudent(Student student);

    Student findStudent(int studentId);

    Student findStudentWithCourse(int Id);

    void updateCourseDetails(int studentId, int courseId, String courseName) throws StudentNotFoundException, CourseNotFoundException;

    void deleteStudent(int studentId) throws StudentNotFoundException;

    void addCourse(int studentId, Course course) throws StudentNotFoundException;

    void removeCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException;

    void addCoStudent(int courseId, Student student) throws CourseNotFoundException;

    List<Student> findAllStudents();


}
