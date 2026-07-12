package com.mapping.dto;

import com.mapping.enums.Gender;

import java.util.List;
import java.util.Objects;

public class StudentDTO {
    private int id;
    private  String first_name;
    private String last_name;
    private Gender gender;
    private String city;
    private String state;
    private String mobile_number;
    private String email_id;
    private List<CourseDTO> courseDTOList;

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getlast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Gender getgender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getcity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getstate() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getmobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getemail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public List<CourseDTO> getcourseDTOList() {
        return courseDTOList;
    }

    public void setCourseDTOList(List<CourseDTO> courseDTOList) {
        this.courseDTOList = courseDTOList;
    }


    @Override
    public String toString() {
        if(Objects.isNull(courseDTOList)){
            return "StudentDTO{" +
                    "id=" + id +
                    ", first_name='" + first_name +
                    ", last_name='" + last_name +
                    ", gender=" + gender +
                    ", city='" + city +
                    ", state='" + state +
                    ", mobile_number='" + mobile_number + '\'' +
                    ", email_id='" + email_id +
                    '}';
        }else{
            return "StudentDTO{" +
                    "id=" + id +
                    ", first_name='" + first_name +
                    ", last_name='" + last_name +
                    ", gender=" + gender +
                    ", city='" + city +
                    ", state='" + state +
                    ", mobile_number='" + mobile_number +
                    ", email_id='" + email_id +
                    ", courseDTOList=" + courseDTOList +
                    '}';
        }

    }
}


