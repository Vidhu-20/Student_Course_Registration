package com.CourseRegistration.course_registration.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private int courseId;
    private String courseName;
    private String duration;
    private double courseFee;
}
