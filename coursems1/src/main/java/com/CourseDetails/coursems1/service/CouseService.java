package com.CourseDetails.coursems1.service;

import java.util.List;

import com.CourseDetails.coursems1.entity.Course;


public interface CouseService {
     Course saveCourse(Course course);
     Course getCourseById(int courseId);
     List <Course> getAllCourse();
     Course updateCourse(int courseId,Course course);
     void deleteCourse(int courseId);
}
