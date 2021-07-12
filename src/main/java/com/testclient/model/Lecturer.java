package com.testclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class Lecturer {
    private Long lecturerId;
    private String foreName;
    private String patronymic;
    private String surName;
    private Integer currentStudentCount;
    private Set<Course> courses;
}
