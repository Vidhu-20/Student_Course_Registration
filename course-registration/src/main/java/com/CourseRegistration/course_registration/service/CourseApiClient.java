package com.CourseRegistration.course_registration.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.CourseRegistration.course_registration.model.CourseDTO;
@FeignClient(value = "COURSE-SERVICE",path="/course")
public interface CourseApiClient {
  @GetMapping("/{courseId}")
    ResponseEntity<CourseDTO> getThisCourse(@PathVariable int courseId);
}
