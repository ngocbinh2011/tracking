package com.example.studenttracking.repository;

import com.example.studenttracking.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
