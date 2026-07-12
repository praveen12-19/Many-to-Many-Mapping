package com.mapping.entity;

import com.mapping.enums.CourseType;
import com.mapping.enums.Grades;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Course {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int course_id;

    @Column(name="coursename",nullable = false)
    private String course_name;

    @Column(name="enrolldate")
    private LocalDate enroll_date;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CourseType course_type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Grades grade;

   @ManyToMany(mappedBy = "courseList")
    private Set<Student> studentList=new HashSet<>();


}
