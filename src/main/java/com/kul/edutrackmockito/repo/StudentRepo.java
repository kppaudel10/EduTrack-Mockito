package com.kul.edutrackmockito.repo;

import com.kul.edutrackmockito.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student", nativeQuery = true)
    List<Student> getStudents();

}
