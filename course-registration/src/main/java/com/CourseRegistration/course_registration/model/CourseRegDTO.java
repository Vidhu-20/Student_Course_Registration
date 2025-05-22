package com.CourseRegistration.course_registration.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CourseRegDTO 
{
private int registrationId;
private LocalDate dateOfReg;
private double feePaid;
private double feesPending;
private String status;
private StudentDTO student;
private CourseDTO course;

}
