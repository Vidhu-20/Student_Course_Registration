package com.CourseDetails.coursems1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CourseDetails.coursems1.entity.Course;
import com.CourseDetails.coursems1.exception.ResourceNotFoundException;
import com.CourseDetails.coursems1.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CouseService{
@Autowired
private CourseRepository courseRepository;


@Override
public Course saveCourse(Course course) {
   courseRepository.save(course);
    return course;
}
@Override
public Course getCourseById(int courseId) {
    Optional <Course> optionalCourse=courseRepository.findById(courseId);
    if(optionalCourse.isEmpty())
    {
        throw new ResourceNotFoundException("Course id not found "+courseId);
    }
    Course course=optionalCourse.get();
    return course;
}
@Override
public List<Course> getAllCourse() {
    List<Course> courses = courseRepository.findAll();
    return courses;
}
@Override
public Course updateCourse(int courseId, Course course) {
    Optional<Course> optionalCourse=courseRepository.findById(courseId);
    if(optionalCourse.isEmpty())
    {
        throw new ResourceNotFoundException("Course not found with id "+courseId);
    }
    Course courseDetails=optionalCourse.get();
    courseDetails.setCourseName(courseDetails.getCourseName());
    courseDetails.setCourseFee(courseDetails.getCourseFee());
    return courseRepository.save(courseDetails);
}
@Override
public void deleteCourse(int courseId) {
    Optional<Course> optionalCourse=courseRepository.findById(courseId);
    if(optionalCourse.isEmpty())
    {
        throw new ResourceNotFoundException("Course not found with id "+courseId);
    }
    Course courseDetails=optionalCourse.get();
    courseRepository.delete(courseDetails);
}

}
