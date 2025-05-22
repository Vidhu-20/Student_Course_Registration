package com.CourseRegistration.course_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CourseRegistration.course_registration.entity.CoureseReg;

@Repository
public interface CourseRegRepo extends JpaRepository<CoureseReg,Integer>{

}
