package com.CourseRegistration.course_registration.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CourseRegistration.course_registration.entity.CoureseReg;
import com.CourseRegistration.course_registration.exception.ResourceNotFoundException;
import com.CourseRegistration.course_registration.model.CourseDTO;
import com.CourseRegistration.course_registration.repository.CourseRegRepo;

@Service
public class CourseRegServiceImpl implements CourseRegService {
    @Autowired
    private CourseRegRepo courseRegRepo;

    @Autowired
    private CourseApiClient courseApiClient;

    @Override
    public CoureseReg createRegistration(CoureseReg coureseReg) {
        CourseDTO courseDto = courseApiClient.getThisCourse(coureseReg.getCourseId()).getBody();

        // Calculate pending fee
        double pendingFee = courseDto.getCourseFee() - coureseReg.getFeePaid();
        coureseReg.setFeesPending(pendingFee);

        // Set status based on fee payment
        if (pendingFee == 0) {
            coureseReg.setStatus("Fully Paid");
        } else {
            coureseReg.setStatus("Fee Pending");
        }

        coureseReg.setDateOfReg(LocalDate.now());
        return courseRegRepo.save(coureseReg);
    }

    @Override
    public List<CoureseReg> getAllRegistrations() {
        return courseRegRepo.findAll();
    }
    @Override
public CoureseReg getRegistrationById(int registrationId) {
    return courseRegRepo.findById(registrationId)
        .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + registrationId));
}

@Override
public CoureseReg updateRegistration(int registrationId, CoureseReg updatedDetails) {
    CoureseReg existingReg = courseRegRepo.findById(registrationId)
        .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + registrationId));

    // Update only necessary fields
    existingReg.setFeePaid(updatedDetails.getFeePaid());

    // Recalculate pending fee and status
    double pendingFee = existingReg.getFeesPending() - updatedDetails.getFeePaid();
    existingReg.setFeesPending(pendingFee);

    if (pendingFee == 0) {
        existingReg.setStatus("Fully Paid");
    } else {
        existingReg.setStatus("Fee Pending");
    }

    return courseRegRepo.save(existingReg);
}

@Override
public void deleteRegistration(int registrationId) {
    CoureseReg existingReg = courseRegRepo.findById(registrationId)
        .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + registrationId));

    courseRegRepo.delete(existingReg);
}
}