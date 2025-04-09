package com.kul.edutrackmockito.utility;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@Component
public class StudentValidator {

    @Value("${student.max-age}")
    private int maxAge;

    public boolean isValid(StudentReqPojo student) {
        return isValidAge(student.getBirthDate())  && student.getEmail() != null && student.getEmail().contains("@")
                && student.getFirstName() != null && !student.getFirstName().isEmpty()
                && student.getLastName() != null && !student.getLastName().isEmpty()
                && student.getContact() != null && student.getContact().matches("\\d{10}");
    }

    public boolean isValidAge(String dateOfBirth) {
        // Calculate the student's age based on the birthdate
        LocalDate birthDate = LocalDate.parse(dateOfBirth);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        // Check if age exceeds the maximum allowed age of 30
        return age <= maxAge;
    }

}
