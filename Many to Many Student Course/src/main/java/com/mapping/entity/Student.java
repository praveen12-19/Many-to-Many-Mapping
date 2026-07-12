package com.mapping.entity;


import com.mapping.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity

public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "firstname", nullable = false)
    private String first_name;

    @Column(name = "lastname", nullable = false)
    private String last_name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "mobileno", nullable = false, length = 10)
    private String mobile_number;

    @Column(name = "emailid", nullable = false)
    private String email_id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_course_table", joinColumns = @JoinColumn(name = "student", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false))
    private Set<Course> courseList=new HashSet<>();


}


