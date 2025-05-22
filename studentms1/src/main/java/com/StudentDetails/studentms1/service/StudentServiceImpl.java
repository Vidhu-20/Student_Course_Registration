package com.StudentDetails.studentms1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentDetails.studentms1.entity.Student;
import com.StudentDetails.studentms1.exception.ResourceNotFoundException;
import com.StudentDetails.studentms1.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
@Autowired
private StudentRepository studentRepository;
@Override
public Student saveStudent(Student student) {
    studentRepository.save(student);
    return student;
}
@Override
public Student getStudentById(int studentId) {
   Optional <Student> optionalStudent=studentRepository.findById(studentId);
   if(optionalStudent.isEmpty())
   {
    throw new ResourceNotFoundException("Student not found with id: "+studentId);
   }
   Student student=optionalStudent.get();
    return student;
}
@Override
public List<Student> getAllStudent() 
{
    List<Student> students=studentRepository.findAll();
    return students;
}
@Override
public Student updateStudent(int studentId, Student studentDetails) {
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    if (optionalStudent.isEmpty()) {
        throw new ResourceNotFoundException("Student not found with id: " + studentId);
    }
    
    Student student = optionalStudent.get();
    student.setFirstName(studentDetails.getFirstName()); 
    student.setLastName(studentDetails.getLastName());
    student.setEmail(studentDetails.getEmail());
    student.setAge(studentDetails.getAge()); 
    return studentRepository.save(student);
}
@Override
public void deleteStudent(int studentId) {
     Optional<Student> optionalStudent = studentRepository.findById(studentId);
		if (optionalStudent.isEmpty()) {
			throw new ResourceNotFoundException("Student not existing with id: " + studentId);
		}		
        Student student=optionalStudent.get();
        studentRepository.delete(student);
    
}


}
