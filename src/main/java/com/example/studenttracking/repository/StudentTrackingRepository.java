package com.example.studenttracking.repository;

import com.example.studenttracking.model.StudentTracking;
import com.example.studenttracking.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentTrackingRepository extends JpaRepository<StudentTracking, Long> {
}