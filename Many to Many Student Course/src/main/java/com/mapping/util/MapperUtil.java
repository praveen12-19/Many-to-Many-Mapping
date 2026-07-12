package com.mapping.util;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.entity.Course;
import com.mapping.entity.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtil {

    public static Student convertStudentDtoToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirst_name(studentDTO.getfirst_name());
        student.setLast_name(studentDTO.getlast_name());
        student.setGender(studentDTO.getgender());
        student.setCity(studentDTO.getcity());
        student.setState(studentDTO.getstate());
        student.setMobile_number(studentDTO.getmobile_number());
        student.setEmail_id(studentDTO.getemail_id());
        Set<Course> courseList=studentDTO.getcourseDTOList()
                .stream()
                .map(courseDTO -> converCourseDtoToEntity(courseDTO)).collect(Collectors.toSet());
        student.setCourseList(courseList);
        return student;
    }

    public static Course converCourseDtoToEntity(CourseDTO courseDTO) {
        Course course=new Course();
        course.setCourse_name(courseDTO.course_name());
        course.setEnroll_date(courseDTO.enroll_date());
        course.setCourse_type(courseDTO.course_type());
        course.setGrade(courseDTO.grade());
        return course;

    }

    public static StudentDTO convertStudentEntityToDtoWithoutCourse(Student student) {
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirst_name(student.getFirst_name());
        studentDTO.setLast_name(student.getLast_name());
        studentDTO.setGender(student.getGender());
        studentDTO.setCity(student.getCity());
        studentDTO.setState(student.getState());
        studentDTO.setMobile_number(student.getMobile_number());
        studentDTO.setEmail_id(student.getEmail_id());
        return studentDTO;
    }


    public static StudentDTO convertStudentEntityToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirst_name(student.getFirst_name());
        studentDTO.setLast_name(student.getLast_name());
        studentDTO.setGender(student.getGender());
        studentDTO.setCity(student.getCity());
        studentDTO.setState(student.getState());
        studentDTO.setMobile_number(student.getMobile_number());
        studentDTO.setEmail_id(student.getEmail_id());
        List<CourseDTO> courseDTOList = student.getCourseList().stream()
                .map(MapperUtil::convertCourseEntityToDtoWithoutStudent).collect(Collectors.toList());
        studentDTO.setCourseDTOList(courseDTOList);
        return studentDTO;
    }

    public static CourseDTO convertCourseEntityToDtoWithoutStudent(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse_id(course.getCourse_id());
        courseDTO.setCourse_name(course.getCourse_name());
        courseDTO.setEnroll_date(course.getEnroll_date());
        courseDTO.setCourse_type(course.getCourse_type());
        courseDTO.setGrade(course.getGrade());
        return courseDTO;
    }


    public static CourseDTO convertCourseEntityTODto(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse_id(course.getCourse_id());
        courseDTO.setCourse_name(course.getCourse_name());
        courseDTO.setEnroll_date(course.getEnroll_date());
        courseDTO.setCourse_type(course.getCourse_type());
        courseDTO.setGrade(course.getGrade());
        List<StudentDTO> studentDTOList=course.getStudentList().stream().map(student -> convertStudentEntityToDtoWithoutCourse(student)).collect(Collectors.toList());
        courseDTO.setStudentDTOList(studentDTOList);
        return courseDTO;
    }


    public static Student convertStudentDtoToEntityWithoutCourse(StudentDTO studentDTO) {
        Student student=new Student();
        student.setFirst_name(studentDTO.getfirst_name());
        student.setLast_name(studentDTO.getlast_name());
        student.setGender(studentDTO.getgender());
        student.setCity(studentDTO.getcity());
        student.setState(studentDTO.getstate());
        student.setMobile_number(studentDTO.getmobile_number());
        student.setEmail_id(studentDTO.getemail_id());
        return student;
    }

    public static Course convertCourseDtoToEntity(CourseDTO courseDTO) {
        Course course=new Course();
        course.setCourse_id(courseDTO.course_id());
        course.setCourse_name(courseDTO.course_name());
        course.setEnroll_date(courseDTO.enroll_date());
        course.setCourse_type(courseDTO.course_type());
        course.setGrade(courseDTO.grade());
        return course;
    }
}
