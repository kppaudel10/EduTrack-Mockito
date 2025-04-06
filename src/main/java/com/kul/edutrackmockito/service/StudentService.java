package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;

import java.util.List;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
public interface StudentService {

    Integer addStudent(StudentReqPojo studentReqPojo);

    List<StudentResPojo> getStudents();

    StudentResPojo getStudentById(Integer id);
}
