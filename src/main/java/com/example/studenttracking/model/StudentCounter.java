package com.example.studenttracking.model;

import lombok.Data;

@Data
public class StudentCounter {
    private Student student;
    private Classes classes;
    private int total;
    private int absentCount;
}
