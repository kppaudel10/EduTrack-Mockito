package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;

import java.text.ParseException;
import java.util.List;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
public interface StudentService {

    Integer addStudent(StudentReqPojo studentReqPojo);

    Integer updateStudent(StudentReqPojo studentReqPojo) throws ParseException;

    List<StudentResPojo> getStudents();

    StudentResPojo getStudentById(Integer id);
}
