package com.example.studenttracking.repository;

import com.example.studenttracking.model.StudentTracking;
import com.example.studenttracking.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface StudentTrackingRepository extends JpaRepository<StudentTracking, Long> {

    List<StudentTracking> findAllByStudentIdAndClassesId(Long studentId, Long classId);

    List<StudentTracking> findAllByClassesIdAndTrackingDate(Long classId, LocalDate localDate);

    StudentTracking findAllByClassesIdAndStudentIdAndTrackingDate(Long classId, Long studentId,  LocalDate localDate);
}