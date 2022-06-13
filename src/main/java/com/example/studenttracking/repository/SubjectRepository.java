package com.example.studenttracking.repository;

import com.example.studenttracking.model.Student;
import com.example.studenttracking.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
