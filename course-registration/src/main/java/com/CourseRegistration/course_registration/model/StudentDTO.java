package com.CourseRegistration.course_registration.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private int age;
}
