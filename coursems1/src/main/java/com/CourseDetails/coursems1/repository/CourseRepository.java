package com.CourseDetails.coursems1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CourseDetails.coursems1.entity.Course;



public interface CourseRepository extends JpaRepository<Course,Integer>
{

}
