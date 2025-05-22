package com.StudentDetails.studentms1.service;

import java.util.List;

import com.StudentDetails.studentms1.entity.Student;

public interface StudentService {

    Student saveStudent(Student student);
    Student getStudentById(int studentId);
    List <Student> getAllStudent();
    Student updateStudent(int studentId,Student student);
    void deleteStudent(int studentId);
}
