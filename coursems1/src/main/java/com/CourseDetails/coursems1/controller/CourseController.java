package com.CourseDetails.coursems1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CourseDetails.coursems1.entity.Course;
import com.CourseDetails.coursems1.service.CouseService;



@RestController
@RequestMapping("/course")
public class CourseController {
@Autowired
private CouseService courseService;
@PostMapping("/save")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		courseService.saveCourse(course);
		return new ResponseEntity<>(course,HttpStatus.CREATED);
	}
@GetMapping("/{courseId}")
	public ResponseEntity<Course> fetchCourseDetails(@PathVariable int courseId) {
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<>(course,HttpStatus.OK);
	}
@GetMapping("/all")
	public ResponseEntity<List<Course>> fetchAllCouse() {
		List<Course> courses = courseService.getAllCourse();
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	@PutMapping("/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course) {    
    Course updatedCourse = courseService.updateCourse(courseId,course);    
    return new ResponseEntity<>(updatedCourse, HttpStatus.OK);    
}
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {	
		
		courseService.deleteCourse(courseId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);		
		return responseEntity;
	}
}