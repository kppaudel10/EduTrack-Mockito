package com.kul.edutrackmockito.utility;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@ExtendWith(MockitoExtension.class)
public class StudentValidatorTest {

    @InjectMocks
    private StudentValidator studentValidator;
    private StudentReqPojo studentReqPojo;

    @BeforeEach
    void setUp() {
        studentValidator = new StudentValidator();
        studentReqPojo = StudentReqPojo.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmail.com")
                .contact("9840459818")
                .birthDate("2000-01-01").build();
    }

    @Test
    void testValidStudent() {
        // verify with valid data
        assertTrue(studentValidator.isValid(studentReqPojo));
        // verify with invalid data
        studentReqPojo.setEmail("messi.gmail.com");
        assertFalse(studentValidator.isValid(studentReqPojo));
    }

}
