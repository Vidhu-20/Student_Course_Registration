package com.StudentDetails.studentms1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentDetails.studentms1.entity.Student;
import com.StudentDetails.studentms1.service.StudentService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/student")
public class StudentController 
{
    @Autowired
    private StudentService studentService;
    @PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return new ResponseEntity<>(student,HttpStatus.CREATED);
	}
    @GetMapping("/{studentId}")
	public ResponseEntity<Student> fetchCourseDetails(@PathVariable int studentId) {
        Student student=studentService.getStudentById(studentId);
        return new ResponseEntity<>(student,HttpStatus.OK);
	}
    @GetMapping("/all")
	public ResponseEntity<List<Student>> fetchAllCustomers() {
		List<Student> students = studentService.getAllStudent();
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
	@PutMapping("/{studentId}")
public ResponseEntity<Student> editStudent(@PathVariable int studentId, @RequestBody Student studentDetails) {    
    Student updatedStudent = studentService.updateStudent(studentId, studentDetails);    
    return new ResponseEntity<>(updatedStudent, HttpStatus.OK);    
}
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {	
		
		studentService.deleteStudent(studentId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);		
		return responseEntity;
	}
	
}
