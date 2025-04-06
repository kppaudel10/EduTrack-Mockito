package com.kul.edutrackmockito.controller;

import com.kul.edutrackmockito.config.GlobalApiResponse;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("")
    public GlobalApiResponse saveStudent(@RequestBody StudentReqPojo studentReqPojo) {
        return new GlobalApiResponse("Successfully saved", studentService.addStudent(studentReqPojo));
    }
}
