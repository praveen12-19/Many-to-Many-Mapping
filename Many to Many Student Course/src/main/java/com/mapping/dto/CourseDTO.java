package com.mapping.dto;

import com.mapping.enums.CourseType;
import com.mapping.enums.Grades;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CourseDTO {
    private int course_id;
    private String course_name;
    private LocalDate enroll_date;
    private CourseType course_type;
    private Grades grade;
    private List<StudentDTO> studentDTOList;

    public int course_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String course_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public LocalDate enroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(LocalDate enroll_date) {
        this.enroll_date = enroll_date;
    }

    public CourseType course_type() {
        return course_type;
    }

    public void setCourse_type(CourseType course_type) {
        this.course_type = course_type;
    }

    public Grades grade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    public List<StudentDTO> studentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

    @Override
    public String toString() {
       if(Objects.isNull(studentDTOList)){
           return "CourseDTO{" +
                   "course_id=" + course_id +
                   ", course_name='" + course_name + '\'' +
                   ", enroll_date=" + enroll_date +
                   ", course_type=" + course_type +
                   ", grade=" + grade  +
                   '}';
       }else{
           return "CourseDTO{" +
                   "course_id=" + course_id +
                   ", course_name='" + course_name + '\'' +
                   ", enroll_date=" + enroll_date +
                   ", course_type=" + course_type +
                   ", grade=" + grade +
                   ", studentDTOList=" + studentDTOList +
                   '}';
       }
    }
}