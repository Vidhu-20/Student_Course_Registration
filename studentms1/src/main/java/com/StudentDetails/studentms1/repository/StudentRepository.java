package com.StudentDetails.studentms1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentDetails.studentms1.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

}
