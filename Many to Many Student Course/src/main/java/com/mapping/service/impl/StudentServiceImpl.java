package com.mapping.service.impl;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.entity.Course;
import com.mapping.entity.Student;
import com.mapping.exception.CourseNotFoundException;
import com.mapping.exception.StudentNotFoundException;
import com.mapping.repository.impl.CourseRepositoryImpl;
import com.mapping.repository.impl.StudentRepositoryImpl;
import com.mapping.service.StudentService;
import com.mapping.util.InputUtil;

import java.util.List;
import java.util.Objects;

public class StudentServiceImpl implements StudentService {
    private StudentRepositoryImpl studentRepository;
    private CourseRepositoryImpl courseRepository;

    public StudentServiceImpl() {
        this.studentRepository=new StudentRepositoryImpl() ;
        this.courseRepository=new CourseRepositoryImpl();
    }
    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = InputUtil.convertStudentDtoToEntity(studentDTO);
        studentRepository.saveStudent(student);
    }

    @Override
    public StudentDTO findStudent(int studentId) throws StudentNotFoundException {
        Student student = studentRepository.findStudent(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException("Student Not Found");
        }
        return InputUtil.convertStudentEntityToDtoWithoutCourse(student);
    }

    @Override
    public StudentDTO findStudentWithCourse(int studentId) throws StudentNotFoundException {
        Student student=studentRepository.findStudentWithCourse(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("student not found ");
        }
        return InputUtil.convertStudentEntityToDto(student);
    }

    @Override
    public void updateCourseDetails(int studentId, int courseId, String courseName) throws StudentNotFoundException, CourseNotFoundException {
        Student student=studentRepository.findStudentWithCourse(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("Student not found");
        }
        studentRepository.updateCourseDetails(studentId,courseId,courseName);

    }

    @Override
    public void deleteStudent(int studentId) throws StudentNotFoundException {
        Student student=studentRepository.findStudent(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("Student not found");
        }
        studentRepository.deleteStudent(studentId);
    }

    @Override
    public void addCourse(int studentId, CourseDTO courseDTO) throws StudentNotFoundException {
        Student student=studentRepository.findStudent(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("Student not found");
        }
        Course course= InputUtil.convertCourseDtoToEntity(courseDTO);
        studentRepository.addCourse(studentId,course);
    }

    @Override
    public void removeCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException {
        Student student=studentRepository.findStudentWithCourse(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("student not found");
        }else if(student.getCourseList().stream().filter(course -> course.getCourse_id()==courseId).findFirst().isEmpty()){
            throw new StudentNotFoundException("Student.course.combinate.not.found");
        }else{
            studentRepository.removeCourse(studentId,courseId);
        }

    }

    @Override
    public void addCoStudent(int courseId, StudentDTO studentDTO) throws CourseNotFoundException {
        Course course= courseRepository.findCourse(courseId);
        if(Objects.isNull(course)){
            throw new CourseNotFoundException("Course not found");
        }else{
            Student student =InputUtil.convertStudentDtoToEntityWithoutCourse(studentDTO);
            studentRepository.addCoStudent(courseId,student);
        }

    }

    @Override
    public List<StudentDTO> findAllStudents() {
        return studentRepository.findAllStudents().stream().map(InputUtil::convertStudentEntityToDto).toList();
    }


}
