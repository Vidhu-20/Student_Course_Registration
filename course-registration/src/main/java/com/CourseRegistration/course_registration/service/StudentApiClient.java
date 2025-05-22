package com.CourseRegistration.course_registration.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.CourseRegistration.course_registration.model.StudentDTO;
@FeignClient(value = "STUDENT-SERVICE",path = "/student")
public interface StudentApiClient {

    @GetMapping("/{studentId}")
    ResponseEntity<StudentDTO> getThisStudent(@PathVariable int studentId);
}
