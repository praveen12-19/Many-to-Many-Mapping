package com.mapping.repository;

import com.mapping.entity.Course;

public interface CourseRepository {
    Course findCourse(int courseId);
    Course findCourseWithStudent(int courseId);
}
