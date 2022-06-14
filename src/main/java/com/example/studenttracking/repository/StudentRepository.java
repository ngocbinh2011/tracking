package com.example.studenttracking.repository;

import com.example.studenttracking.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByClassesId(Long id);

    Student findByCode(String code);
}
