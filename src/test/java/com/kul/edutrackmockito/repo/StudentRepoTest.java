package com.kul.edutrackmockito.repo;

import com.kul.edutrackmockito.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@ExtendWith(MockitoExtension.class)
public class StudentRepoTest {

    @Mock
    private StudentRepo studentRepo;
    private List<Student> students;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        students = new ArrayList<>();
        students.add(new Student(1, "Kul", "Paudel", "kulpaudel@gmail.com", "9840459818",
                simpleDateFormat.parse("2000-01-01")));
        students.add(new Student(2, "Sita", "Karki", "sita.karki@gmail.com", "9812345678",
                simpleDateFormat.parse("1998-05-15")));
        students.add(new Student(3, "Ram", "Thapa", "ram.thapa@gmail.com", "9801122334",
                simpleDateFormat.parse("1997-03-10")));
        students.add(new Student(4, "Bina", "Shrestha", "bina.shrestha@gmail.com", "9856781234",
                simpleDateFormat.parse("2001-07-21")));
        students.add(new Student(5, "Kiran", "Rai", "kiran.rai@gmail.com", "9845567890",
                simpleDateFormat.parse("1999-11-30")));
    }

    @Test
    void testGetAllStudents() {
        when(studentRepo.getStudents()).thenReturn(students);

        List<Student> results = studentRepo.getStudents();

        assertNotNull(results);
        assertEquals(students.size(), results.size());
    }

}
