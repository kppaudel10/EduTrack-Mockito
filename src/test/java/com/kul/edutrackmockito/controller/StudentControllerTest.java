package com.kul.edutrackmockito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

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

    private List<StudentReqPojo> studentList = new ArrayList<>();

    private StudentReqPojo studentReqPojo;

    @BeforeEach
    void setStudentResPojo() {
        studentReqPojo = new StudentReqPojo(1, "Kul", "Paudel", "2000-01-01",
                "kulpaudel56@gmail.com", "9840459818");
        studentList.add(new StudentReqPojo(2, "Anisha", "Shrestha", "1998-05-12",
                "anisha.shrestha@gmail.com", "9812345678"));
        studentList.add(new StudentReqPojo(3, "Bibek", "Thapa", "1995-11-23",
                "bibek.thapa@yahoo.com", "9801234567"));
        studentList.add(new StudentReqPojo(4, "Sita", "Gurung", "2001-07-15",
                "sita.gurung@outlook.com", "9845123456"));
        studentList.add(new StudentReqPojo(5, "Ram", "Karki", "1999-03-30",
                "ram.karki@gmail.com", "9823456789"));
        studentList.add(new StudentReqPojo(6, "Nirajan", "Bhandari", "2002-12-01",
                "nirajan.bhandari@mail.com", "9867890123"));
    }

    @Test
    void addStudentTest() throws Exception {
        Mockito.when(studentService.addStudent(studentReqPojo)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/student")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(studentReqPojo))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
        log.info("Result: {}", result);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
