package com.CourseRegistration.course_registration.service;

import java.time.LocalDate;
import java.util.List;

import com.CourseRegistration.course_registration.entity.CoureseReg;


public interface CourseRegService {
 CoureseReg createRegistration(CoureseReg coureseReg);
 public List <CoureseReg> getAllRegistrations();
 CoureseReg getRegistrationById(int registrationId);
 CoureseReg updateRegistration(int registrationId, CoureseReg updatedDetails);
  void deleteRegistration(int registrationId);


}
