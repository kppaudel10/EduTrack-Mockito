package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.model.Student;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;
import com.kul.edutrackmockito.repo.StudentRepo;
import com.kul.edutrackmockito.utility.StudentValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private StudentServiceImpl studentService;

    @Mock
    private StudentValidator studentValidator;
    @Mock
    private SimpleDateFormat dateFormat;

    private StudentReqPojo studentReqPojo;

    private final List<Student> studentList = new ArrayList<>();

    @BeforeEach
    void setUp() throws ParseException {
        studentList.add(Student.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmial.com")
                .contact("9840459818")
                .dateOfBirth(dateFormat.parse("1985-01-01")).build());

        studentList.add(Student.builder()
                .id(2)
                .firstName("Kul")
                .lastName("Paudel")
                .email("kulpaudel@gmail.com")
                .contact("9800000002")
                .dateOfBirth(dateFormat.parse("1995-05-05"))
                .build());

        studentReqPojo = StudentReqPojo.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmail.com")
                .contact("9840459818")
                .birthDate("1985-01-01").build();
    }

    @Test
    void testAddStudentWithValidData() {
        when(studentValidator.isValid(studentReqPojo)).thenReturn(true);
        when(studentRepo.save(any(Student.class))).thenReturn(any());
        int result = studentService.addStudent(studentReqPojo);
        assertEquals(1, result);
        verify(studentRepo, times(1)).save(any(Student.class));
    }

    @Test
    void testAddStudentWithInvalidData() {
        when(studentValidator.isValid(studentReqPojo)).thenReturn(false);
        // Assert that an exception is thrown when validation fails
        assertThrows(RuntimeException.class, () -> studentService.addStudent(studentReqPojo));
        // Verify that save is never called
        verify(studentRepo, never()).save(any(Student.class));
    }

    @Test
    void testFindStudentById() {
        // We are saying: “Whenever this method is called with ID 2, return the student object above.”
        when(studentRepo.findById(1)).thenReturn(Optional.of(studentList.get(0)));
        // Calling the actual service method
        StudentResPojo result = studentService.getStudentById(1);
        // Verifying the output
        assertNotNull(result);
        assertEquals("Leonel", result.getFirstName());
        // checks repo is called during the method execution
        verify(studentRepo).findById(1);
    }

    @Test
    void testStudentNotFound() {
        // verify the service correctly through EntityNotFoundException correctly
        when(studentRepo.findById(anyInt())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getStudentById(anyInt()));
        assertEquals("Student not found", exception.getMessage());

    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentValidator.isValid(studentReqPojo)).thenReturn(true);
        when(studentRepo.findById(1)).thenReturn(Optional.of(studentList.get(0)));
        when(studentRepo.save(any(Student.class))).thenReturn(studentList.get(0));

        studentReqPojo.setId(1);
        studentReqPojo.setEmail("leonal@gmail.com");
        int result = studentService.updateStudent(studentReqPojo);
        assertEquals(1, result);

        StudentResPojo studentResPojo = studentService.getStudentById(1);
        assertNotNull(studentResPojo);

        assertEquals(studentReqPojo.getEmail(), studentResPojo.getEmail());
        verify(studentRepo).save(any(Student.class));
    }

    @Test
    void testGetAllStudents() {
        // Mock the repo method
        when(studentRepo.getStudents()).thenReturn(studentList);

        // Act: call the service
        List<StudentResPojo> result = studentService.getStudents();

        // Assert: verify returned list matches expectations
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Leonel", result.get(0).getFirstName());
        assertEquals("Kul", result.get(1).getFirstName());

        // Verify the repo method was called once
        verify(studentRepo, times(1)).getStudents();
    }


}
