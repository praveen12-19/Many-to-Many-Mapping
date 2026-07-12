package com.mapping.util;

import com.mapping.dto.CourseDTO;
import com.mapping.dto.StudentDTO;
import com.mapping.entity.Course;
import com.mapping.entity.Student;
import com.mapping.enums.CourseType;
import com.mapping.enums.Gender;
import com.mapping.enums.Grades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputUtil {
    public static int acceptOption(Scanner sc) {
        System.out.println("1. add new student.");
        System.out.println("2. fetch student details.");
        System.out.println("3. update student details of owner.");
        System.out.println("4. delete student details.");
        System.out.println("5. fetch course details.");
        System.out.println("6. add new course.");
        System.out.println("7. remove course details.");
        System.out.println("8. add co-student.");
        System.out.println("9. fetch all student details.");
        int menuOption = sc.nextInt();
        if (menuOption == 1 || menuOption == 2 || menuOption == 3 || menuOption == 4 || menuOption == 5
                || menuOption == 6 || menuOption == 7 || menuOption == 8||menuOption==9) {
            return menuOption;
        } else {
            return acceptOption(sc);
        }
    }
    public static boolean wantToContinue(Scanner scanner) {
        System.out.println("Press Y to continue and N to exit.");
        char choice = scanner.next().toUpperCase().charAt(0);
        return 'Y' == choice;
    }

    public static StudentDTO studentDetails(Scanner sc) {
        System.out.println("Enter first name of student:");
        String firstName = sc.next();
        System.out.println("Enter last name of student:");
        String lastName = sc.next();
        System.out.println("Enter gender of student:" + Arrays.asList(Gender.values()).toString());
        String gender = sc.next().toUpperCase();
        System.out.println("Enter city of student:");
        String city = sc.next();
        System.out.println("Enter state of student:");
        String state = sc.next();
        System.out.println("Enter mobile number of student:");
        String mobileNumber = sc.next();
        System.out.println("Enter email id of student:");
        String emailId = sc.next();
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirst_name(firstName);
            studentDTO.setLast_name(lastName);
            studentDTO.setGender(Gender.valueOf(gender));
            studentDTO.setCity(city);
            studentDTO.setState(state);
            studentDTO.setMobile_number(mobileNumber);
            studentDTO.setEmail_id(emailId);
            return studentDTO;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return studentDetails(sc);
        }
    }

    public static CourseDTO courseDetails(Scanner sc) {
        System.out.println("Enter name of course:");
        String courseName = sc.next();
        System.out.println("Enter enroll date (dd-MM-yyyy):");
        String enrolldate = sc.next();
        System.out.println("Enter course type:" + Arrays.asList(CourseType.values()).toString());
        String coursetype = sc.next().toUpperCase();
        System.out.println("Enter grade:" + Arrays.asList(Grades.values()).toString());
        String grade = sc.next().toUpperCase();
        try {
            CourseDTO petDTO = new CourseDTO();
            petDTO.setCourse_name(courseName);
            petDTO.setEnroll_date(convertStringToDate(enrolldate));
            petDTO.setCourse_type(CourseType.valueOf(coursetype));
            petDTO.setGrade(Grades.valueOf(grade));
            return petDTO;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return courseDetails(sc);
        }
    }

    private static LocalDate convertStringToDate(String enrolldate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(enrolldate, format);
    }

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

    public static int acceptStudentIdtoOperate(Scanner sc) {
        System.out.println("Enter the id of student: ");
        return sc.nextInt();
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
                .map(InputUtil::convertCourseEntityToDtoWithoutStudent).collect(Collectors.toList());
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

    public static int acceptCourseIdtoOperate(Scanner sc) {
        System.out.println("Enter the courseID:");
        return sc.nextInt();
    }

    public static String acceptCourseNameToUpdate(Scanner sc) {
        System.out.println("Enter the update course name: ");
        return sc.next();
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

    public static CourseDTO acceptCourseDetailsToSave(Scanner sc) {
        System.out.println("Enter the course name: ");
        String coursename = sc.next();
        System.out.println("Enter Course Enroll Date (dd-MM-yyyy): ");
        String enrolldate = sc.next();
        System.out.println("Enter Course Type: " + Arrays.asList(CourseType.values()));
        String coursetype = sc.next().toUpperCase();
        System.out.println("Enter the student grade: " + Arrays.asList(Grades.values()));
        String grade = sc.next().toUpperCase();

        try {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourse_name(coursename);
            courseDTO.setEnroll_date(convertStringToDate(enrolldate));
            courseDTO.setCourse_type(CourseType.valueOf(coursetype));
            courseDTO.setGrade(Grades.valueOf(grade));
            return courseDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return acceptCourseDetailsToSave(sc);
        }
    }

    public static StudentDTO acceptStudentDetailsToSave(Scanner sc) {
        System.out.println("Enter first name of student:");
        String firstName = sc.next();
        System.out.println("Enter last name of student:");
        String lastName = sc.next();
        System.out.println("Enter gender of student:" + Arrays.asList(Gender.values()).toString());
        String gender = sc.next().toUpperCase();
        System.out.println("Enter city of student:");
        String city = sc.next();
        System.out.println("Enter state of student:");
        String state = sc.next();
        System.out.println("Enter mobile number of student:");
        String mobileNumber = sc.next();
        System.out.println("Enter email id of student:");
        String emailId = sc.next();
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirst_name(firstName);
            studentDTO.setLast_name(lastName);
            studentDTO.setGender(Gender.valueOf(gender));
            studentDTO.setCity(city);
            studentDTO.setState(state);
            studentDTO.setMobile_number(mobileNumber);
            studentDTO.setEmail_id(emailId);
            return studentDTO;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return acceptStudentDetailsToSave(sc);
        }
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
