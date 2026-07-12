package com.mapping.repository.impl;

import com.mapping.config.DBConfig;
import com.mapping.entity.Course;
import com.mapping.repository.CourseRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class CourseRepositoryImpl implements CourseRepository {
    private SessionFactory sessionFactory= DBConfig.getSessionFactory();
    @Override
    public Course findCourse(int courseId) {
        try(Session session=sessionFactory.openSession()){
            return session.find(Course.class,courseId);
        }
    }

    @Override
    public Course findCourseWithStudent(int courseId) {
        try(Session session=sessionFactory.openSession()){
            Course course=session.find(Course.class,courseId);
            if(Objects.nonNull(course)){
                Hibernate.initialize(course.getStudentList());
            }
            return course;
        }
    }
}
