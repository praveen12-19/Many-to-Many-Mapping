package com.mapping.main;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.service.CourseService;
import com.mapping.service.StudentService;
import com.mapping.service.impl.CourseServiceImpl;
import com.mapping.service.impl.StudentServiceImpl;
import com.mapping.util.InputUtil;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app=new App();
        app.run();
    }
    public void run(){
        StudentService studentService=new StudentServiceImpl();
        CourseService courseService=new CourseServiceImpl();

        try(Scanner sc=new Scanner(System.in)){
            do{
                int option= InputUtil.acceptOption(sc);
                switch(option){
                    case 1:
                        StudentDTO studentDTO=InputUtil.studentDetails(sc);
                        CourseDTO courseDTO=InputUtil.courseDetails(sc);
                        List<CourseDTO> courseDTOList=new ArrayList<>();
                        courseDTOList.add(courseDTO);
                        studentDTO.setCourseDTOList(courseDTOList);
                        studentService.saveStudent(studentDTO);
                        System.out.println("Student Details Added");
                        break;
                    case 2:
                        int studentid=InputUtil.acceptStudentIdtoOperate(sc);
                        studentDTO=studentService.findStudent(studentid);
                        System.out.println("Student has been fetched Successfully");
                        System.out.println(studentDTO);
                        studentDTO=studentService.findStudentWithCourse((studentid));
                        System.out.println("Student with course has been fetched ");
                        System.out.println(studentDTO);
                        break;
                    case 3:
                        int studentId=InputUtil.acceptStudentIdtoOperate(sc);
                        int courseId=InputUtil.acceptCourseIdtoOperate(sc);
                        String courseName=InputUtil.acceptCourseNameToUpdate(sc);
                        studentService.updateCourseDetails(studentId,courseId,courseName);
                        System.out.println("Student details are updated ");
                        break;
                    case 4:
                        studentId=InputUtil.acceptStudentIdtoOperate(sc);
                        studentService.deleteStudent(studentId);
                        System.out.println("Student has been deleted");
                        break;
                    case 5:
                        courseId=InputUtil.acceptCourseIdtoOperate(sc);
                        courseDTO= courseService.findCourse(courseId);
                        System.out.println("Course has beem fetched");
                        System.out.println(courseDTO);
                        courseDTO=courseService.findCourseWithStudent(courseId);
                        System.out.println("Course with student has been fetched");
                        System.out.println(courseDTO);
                        break;

                    case 6:
                        studentId=InputUtil.acceptStudentIdtoOperate(sc);
                        courseDTO=InputUtil.acceptCourseDetailsToSave(sc);
                        studentService.addCourse(studentId,courseDTO);
                        System.out.println("Course has been added ");
                        break;
                    case 7:
                        studentId=InputUtil.acceptStudentIdtoOperate(sc);
                        courseId=InputUtil.acceptCourseIdtoOperate(sc);
                        studentService.removeCourse(studentId,courseId);
                        System.out.println(" Course has been removed ");
                        break;
                    case 8:
                        studentDTO=InputUtil.acceptStudentDetailsToSave(sc);
                        courseId=InputUtil.acceptCourseIdtoOperate(sc);
                        studentService.addCoStudent(courseId,studentDTO);
                        System.out.println("CO-Student has been added");
                        break;
                    case 9:
                        List<StudentDTO> studentDTOList=studentService.findAllStudents();
                        studentDTOList.forEach(System.out::println);
                        System.out.println("Student has been fetched");
                        break;

                    default:
                        System.out.println("Invalid choice ");

                }
            }while(InputUtil.wantToContinue(sc));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
