package com.example.studenttracking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_tracking")
@Data
public class StudentTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Classes classes;

    @ManyToOne
    private Student student;

    @Column(name = "tracking_date")
    private LocalDate trackingDate;
}