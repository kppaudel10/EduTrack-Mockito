package com.kul.edutrackmockito.utility;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
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
    void setUp() throws Exception {
        studentValidator = new StudentValidator();
        studentReqPojo = StudentReqPojo.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmail.com")
                .contact("9840459818")
                .birthDate("2000-01-01").build();

        Field field = StudentValidator.class.getDeclaredField("maxAge");
        field.setAccessible(true);
        field.set(studentValidator, 30);
    }

    @Test
    void testValidStudent() {
        // verify with valid data
        assertTrue(studentValidator.isValid(studentReqPojo));
        // verify with invalid data
        studentReqPojo.setEmail("messi.gmail.com");
        assertFalse(studentValidator.isValid(studentReqPojo));
    }

    @Test
    void testStudentAge() {
        // Test with valid age (born in 2000)
        assertTrue(studentValidator.isValidAge(studentReqPojo.getBirthDate()));

        // Change birthdate to an old date (1990) => age > 30
        studentReqPojo.setBirthDate("1990-01-01");
        assertFalse(studentValidator.isValidAge(studentReqPojo.getBirthDate()));
    }
}
