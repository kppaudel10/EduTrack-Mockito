package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.model.Student;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;
import com.kul.edutrackmockito.repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public Integer addStudent(StudentReqPojo studentReqPojo) {
        Student student = Student.builder()
                .id(studentReqPojo.getId())
                .firstName(studentReqPojo.getFirstName())
                .lastName(studentReqPojo.getLastName())
                .dateOfBirth(studentReqPojo.getBirthDate()).build();
        studentRepo.save(student);
        return student.getId();
    }

    @Override
    public List<StudentResPojo> getStudents() {
        return studentRepo.getStudents();
    }

    @Override
    public StudentResPojo getStudentById(Integer id) {
        Student student = studentRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("student not found"));
        return entityToPojo(student);
    }

    private StudentResPojo entityToPojo(Student student) {
        return StudentResPojo.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getDateOfBirth())
                .build();
    }

}
