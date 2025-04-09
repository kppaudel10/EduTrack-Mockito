package com.kul.edutrackmockito.service;

import com.kul.edutrackmockito.model.Student;
import com.kul.edutrackmockito.pojo.StudentReqPojo;
import com.kul.edutrackmockito.pojo.StudentResPojo;
import com.kul.edutrackmockito.repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Integer addStudent(StudentReqPojo studentReqPojo) {
        Student student;
        try {
            student = Student.builder()
                    .id(studentReqPojo.getId())
                    .firstName(studentReqPojo.getFirstName())
                    .lastName(studentReqPojo.getLastName())
                    .email(studentReqPojo.getEmail())
                    .contact(studentReqPojo.getContact())
                    .dateOfBirth(simpleDateFormat.parse(studentReqPojo.getBirthDate())).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        studentRepo.save(student);
        return student.getId();
    }

    @Override
    public Integer updateStudent(StudentReqPojo studentReqPojo) throws ParseException {
        Optional<Student> optionalStudent = studentRepo.findById(studentReqPojo.getId());
        if (optionalStudent.isEmpty()) {
            throw new EntityNotFoundException("Student with id " + studentReqPojo.getId() + " not found");
        }
        Student student = optionalStudent.get();
        student.setFirstName(studentReqPojo.getFirstName());
        student.setLastName(studentReqPojo.getLastName());
        student.setEmail(studentReqPojo.getEmail());
        student.setContact(studentReqPojo.getContact());
        student.setDateOfBirth(simpleDateFormat.parse(studentReqPojo.getBirthDate()));
        studentRepo.save(student);
        return student.getId();
    }

    @Override
    public List<StudentResPojo> getStudents() {
        List<Student> studentList = studentRepo.getStudents();
        List<StudentResPojo> studentResPojoList = new ArrayList<>();
        for (Student student : studentList) {
            studentResPojoList.add(entityToPojo(student));
        }
        return studentResPojoList;
    }

    @Override
    public StudentResPojo getStudentById(Integer id) {
        Student student = studentRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Student not found"));
        return entityToPojo(student);
    }

    private StudentResPojo entityToPojo(Student student) {
        return StudentResPojo.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getDateOfBirth())
                .email(student.getEmail())
                .contact(student.getContact())
                .build();
    }

}
