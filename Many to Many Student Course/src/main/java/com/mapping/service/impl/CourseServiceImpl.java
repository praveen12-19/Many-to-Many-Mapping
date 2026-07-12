package com.mapping.service.impl;

import com.mapping.dto.CourseDTO;
import com.mapping.entity.Course;
import com.mapping.exception.CourseNotFoundException;
import com.mapping.repository.impl.CourseRepositoryImpl;
import com.mapping.service.CourseService;
import com.mapping.util.MapperUtil;

import java.util.Objects;

public class CourseServiceImpl implements CourseService {

        private CourseRepositoryImpl courseRepository;


        public CourseServiceImpl() {
            this.courseRepository = new CourseRepositoryImpl();
        }


    @Override
    public CourseDTO findCourse(int courseId) throws CourseNotFoundException {
        Course course=courseRepository.findCourse(courseId);
        if(Objects.isNull(course)){
            throw new CourseNotFoundException("Course not found");
        }
        return MapperUtil.convertCourseEntityToDtoWithoutStudent(course);
    }

    @Override
    public CourseDTO findCourseWithStudent(int courseId) throws CourseNotFoundException {
        Course course=courseRepository.findCourseWithStudent(courseId);
        if(Objects.isNull(course)){
            throw new CourseNotFoundException("Course not found");
        }
        return MapperUtil.convertCourseEntityTODto(course);
    }
}


