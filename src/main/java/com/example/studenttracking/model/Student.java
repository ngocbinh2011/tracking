package com.example.studenttracking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "students")
    private Set<Classes> classes = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentTracking> studentTrackings = new HashSet<>();
}
