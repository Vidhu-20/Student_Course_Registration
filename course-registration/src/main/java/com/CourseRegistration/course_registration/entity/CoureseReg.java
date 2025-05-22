package com.CourseRegistration.course_registration.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="Course_Reg")
public class CoureseReg 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int registrationId;
private int studentId;
private int courseId;
private LocalDate dateOfReg;
private double feePaid;
private double feesPending;
private String status;

}

