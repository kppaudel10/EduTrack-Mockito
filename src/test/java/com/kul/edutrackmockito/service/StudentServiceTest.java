package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.model.Student;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;
import com.kul.edutrackmockito.repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private StudentReqPojo studentReqPojo;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @BeforeEach
    void setUp() {
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
                .id(2)
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

    @Test
    void testFindStudentById() throws ParseException {
        Student student = Student.builder()
                .id(2)
                .firstName("Bishal")
                .lastName("Thakur")
                .email("bishal@gmail.com")
                .contact("9840459812")
                .dateOfBirth(dateFormat.parse("1995-05-05")).build();
        // We are saying: “Whenever this method is called with ID 2, return the student object above.”
        when(studentRepo.findById(2)).thenReturn(Optional.of(student));
        // Calling the actual service method
        StudentResPojo result = studentService.getStudentById(2);
        // Verifying the output
        assertNotNull(result);
        assertEquals("Bishal", result.getFirstName());
        // checks repo is called during the method execution
        verify(studentRepo).findById(2);
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
        Student student = Student.builder()
                .id(1)
                .firstName("Leonel")
                .lastName("Messi")
                .email("messi@gmial.com")
                .contact("9840459818")
                .dateOfBirth(dateFormat.parse("1985-01-01")).build();

        when(studentRepo.findById(1)).thenReturn(Optional.of(student));
        when(studentRepo.save(any(Student.class))).thenReturn(student);

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
    void testGetAllStudents() throws ParseException {
        // Arrange: create mock response
        StudentResPojo student1 = StudentResPojo.builder()
                .id(1)
                .firstName("Kul")
                .lastName("Paudel")
                .email("kul@example.com")
                .contact("9800000001")
                .birthDate(dateFormat.parse("1990-01-01"))
                .build();

        StudentResPojo student2 = StudentResPojo.builder()
                .id(2)
                .firstName("Bishal")
                .lastName("Thakur")
                .email("bishal@example.com")
                .contact("9800000002")
                .birthDate(dateFormat.parse("1995-05-05"))
                .build();

        List<StudentResPojo> mockList = List.of(student1, student2);

        // Mock the repo method
        when(studentRepo.getStudents()).thenReturn(mockList);

        // Act: call the service
        List<StudentResPojo> result = studentService.getStudents();

        // Assert: verify returned list matches expectations
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Kul", result.get(0).getFirstName());
        assertEquals("Bishal", result.get(1).getFirstName());

        // Verify the repo method was called once
        verify(studentRepo, times(1)).getStudents();
    }


}
