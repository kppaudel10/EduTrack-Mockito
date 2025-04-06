package com.kul.edutrackmockito.repo;

import com.kul.edutrackmockito.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
public interface StudentRepo extends JpaRepository<Student, Long> {

}
