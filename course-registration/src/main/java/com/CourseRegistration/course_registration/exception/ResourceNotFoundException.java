package com.CourseRegistration.course_registration.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
