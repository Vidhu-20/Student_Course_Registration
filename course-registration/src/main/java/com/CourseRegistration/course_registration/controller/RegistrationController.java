package com.CourseRegistration.course_registration.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CourseRegistration.course_registration.entity.CoureseReg;
import com.CourseRegistration.course_registration.service.CourseRegService;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private CourseRegService courseRegService;

    @PostMapping("/create")
    public ResponseEntity<CoureseReg> createRegister(@RequestBody CoureseReg coureseReg){
        CoureseReg createdRegistration = courseRegService.createRegistration(coureseReg);
        return ResponseEntity.ok().body(createdRegistration);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CoureseReg>> getAllRegistration(){
        List<CoureseReg> registrations =courseRegService.getAllRegistrations();
        return ResponseEntity.ok().body(registrations);
    }
    @GetMapping("/{registrationId}")
public ResponseEntity<CoureseReg> getRegistration(@PathVariable int registrationId) {
    CoureseReg registration = courseRegService.getRegistrationById(registrationId);
    return ResponseEntity.ok().body(registration);
}

@PutMapping("/{registrationId}")
public ResponseEntity<CoureseReg> updateRegistration(@PathVariable int registrationId, @RequestBody CoureseReg updatedDetails) {
    CoureseReg updatedRegistration = courseRegService.updateRegistration(registrationId, updatedDetails);
    return ResponseEntity.ok().body(updatedRegistration);
}

@DeleteMapping("/{registrationId}")
public ResponseEntity<Void> deleteRegistration(@PathVariable int registrationId) {
    courseRegService.deleteRegistration(registrationId);
    return ResponseEntity.ok().build();
}

}
