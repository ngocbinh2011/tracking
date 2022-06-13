package com.example.studenttracking.repository;

import com.example.studenttracking.model.Classes;
import com.example.studenttracking.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
}