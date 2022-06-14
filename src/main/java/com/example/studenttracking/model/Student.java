package com.example.studenttracking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    private Set<Classes> classes = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentTracking> studentTrackings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(code, student.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
