package com.kul.edutrackmockito.utility;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import org.springframework.stereotype.Component;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@Component
public class StudentValidator {

    public boolean isValid(StudentReqPojo student) {
        return student.getEmail() != null && student.getEmail().contains("@")
                && student.getFirstName() != null && !student.getFirstName().isEmpty()
                && student.getLastName() != null && !student.getLastName().isEmpty()
                && student.getContact() != null && student.getContact().matches("\\d{10}");
    }

}
