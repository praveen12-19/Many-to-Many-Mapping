package com.mapping.repository.impl;

import com.mapping.config.DBConfig;
import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.entity.Course;
import com.mapping.entity.Student;
import com.mapping.exception.CourseNotFoundException;
import com.mapping.exception.StudentNotFoundException;
import com.mapping.repository.StudentRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class StudentRepositoryImpl implements StudentRepository {
    private static final SessionFactory SESSION_FACTORY= DBConfig.getSessionFactory();

    @Override
    public void saveStudent(Student student) {
        try(Session session=SESSION_FACTORY.openSession()){
            Transaction tx=session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    @Override
    public Student findStudent(int studentId) {
        try(Session session=SESSION_FACTORY.openSession()){
            Student student=session.find(Student.class,studentId);
            return student;
        }
    }

    @Override
    public Student findStudentWithCourse(int studentId) {
        try(Session session=SESSION_FACTORY.openSession()){
            Student student=session.find(Student.class,studentId);
            if(Objects.nonNull(student)){
                Hibernate.initialize((student.getCourseList()));
            }
            return  student;
        }
    }

    @Override
    public void updateCourseDetails(int studentId, int courseId, String courseName) throws StudentNotFoundException, CourseNotFoundException {
        try(Session session=SESSION_FACTORY.openSession()){
            Transaction tx=session.beginTransaction();
            Student student=session.find(Student.class,studentId);
            if(Objects.nonNull(student)){
                student.getCourseList().stream().filter(course -> course.getCourse_id()==courseId).findFirst().ifPresent(course -> course.setCourse_name(courseName));
                session.merge(student);
            }
            tx.commit();
        }
    }

    @Override
    public void deleteStudent(int studentId) throws StudentNotFoundException {
        try(Session session=SESSION_FACTORY.openSession()){
            Transaction tx=session.beginTransaction();
            Student student=session.find(Student.class,studentId);
            if(Objects.nonNull(student)){
                student.getCourseList().stream().filter(course -> course.getStudentList().size()==1).forEach(student.getCourseList()::remove);
                session.remove(student);
            }
            tx.commit();
        }
    }

    @Override
    public void addCourse(int studentId, Course course) throws StudentNotFoundException {
        try(Session session=SESSION_FACTORY.openSession()) {
            Transaction tx=session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            if (Objects.nonNull(student)) {
                student.getCourseList().add(course);
                session.merge(student);
            }
            tx.commit();
        }
    }

    @Override
    public void removeCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException {
        try(Session session=SESSION_FACTORY.openSession()) {
            Transaction tx=session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            if (Objects.nonNull(student)) {
                student.getCourseList().stream().filter(course -> course.getCourse_id()==courseId).filter(course -> course.getStudentList().size()==1).findFirst().ifPresent(session::remove);
                student.getCourseList().removeIf(course -> course.getCourse_id()==courseId);
                session.persist(student);
                tx.commit();
            }
        }
    }

    @Override
    public void addCoStudent(int courseId, Student student) throws CourseNotFoundException {
        try(Session session=SESSION_FACTORY.openSession()) {
            Transaction tx=session.beginTransaction();
            Course course = session.find(Course.class, courseId);
            if (Objects.nonNull(student)) {
                student.getCourseList().add(course);
                session.persist(student);
                tx.commit();
            }

        }
    }

    @Override
    public List<Student> findAllStudents() {
        String hql="SELECT o FROM Student o";
        try(Session session=SESSION_FACTORY.openSession()){
            return session.createSelectionQuery(hql,Student.class).getResultList();
        }
    }


}
