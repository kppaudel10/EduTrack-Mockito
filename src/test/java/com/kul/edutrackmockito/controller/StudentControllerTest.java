package com.kul.edutrackmockito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;
import com.kul.edutrackmockito.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-08
 **/
@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;
    private StudentReqPojo studentReqPojo;
    private StudentResPojo studentResPojo;
    private ObjectMapper objectMapper;


    @BeforeEach
    void setStudentResPojo() throws ParseException {
        objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        studentReqPojo = StudentReqPojo.builder()
                .id(1)
                .firstName("Kul")
                .lastName("Paudel")
                .email("kul@gmail.com")
                .contact("9800000000")
                .birthDate("1995-01-01")
                .build();

        studentResPojo = StudentResPojo.builder()
                .id(1)
                .firstName("Kul")
                .lastName("Paudel")
                .email("kul@gmail.com")
                .birthDate(dateFormat.parse("1990-01-01"))
                .contact("9800000001")
                .build();
    }

    @Test
    void testSaveStudent() throws Exception {
        Mockito.when(studentService.addStudent(any(StudentReqPojo.class))).thenReturn(1);

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentReqPojo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully saved"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    void testUpdateStudent() throws Exception {
        studentReqPojo.setEmail("updatedkul@gmail.com");

        Mockito.when(studentService.updateStudent(any(StudentReqPojo.class))).thenReturn(1);

        mockMvc.perform(put("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentReqPojo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully saved"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    void testGetStudents() throws Exception {

        Mockito.when(studentService.getStudents()).thenReturn(List.of(studentResPojo));

        mockMvc.perform(get("/api/v1/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetch"))
                .andExpect(jsonPath("$.data[0].firstName").value("Kul"));
    }

    @Test
    void testGetStudentById() throws Exception {

        Mockito.when(studentService.getStudentById(eq(1))).thenReturn(studentResPojo);

        mockMvc.perform(post("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully saved"))
                .andExpect(jsonPath("$.data.firstName").value("Kul"));
    }


}
