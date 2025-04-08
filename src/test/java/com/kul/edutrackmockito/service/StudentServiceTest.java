package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.model.Student;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-08
 **/
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    /**
     * reason of using mock here:
     * This tells Mockito to mock the StudentRepo bean. No actual DB call happens —
     * Mockito gives a fake version you can control.
     */
    @Mock
    private StudentRepo studentRepo;

    /**
     * Reason of using @InjectMock here:
     * This tells Mockito: “Inject the mocked studentRepo into studentService.”
     * This means studentService will use the mocked version of studentRepo when its methods are called.
     */
    @InjectMocks
    private StudentService studentService;

    private StudentReqPojo studentReqPojo;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @BeforeEach
    void setUp() {
        //It initializes all @Mock and @InjectMocks annotated fields, so your mocks are active.
        MockitoAnnotations.openMocks(this);

        studentReqPojo = StudentReqPojo.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmail.com")
                .contact("9840459818")
                .birthDate("1985-01-01").build();
    }

    @Test
    void testAddStudent() throws Exception {
        Student student = Student.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmial.com")
                .contact("9840459818")
                .dateOfBirth(dateFormat.parse("1985-01-01")).build();
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        int result = studentService.addStudent(studentReqPojo);
        assertEquals(1, result);
        verify(studentRepo, times(1)).save(any(Student.class));
    }


}
