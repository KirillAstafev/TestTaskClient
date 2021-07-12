package com.testclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class Course {
    private Long courseId;
    private String courseName;
    private Integer workLoad;
    private Set<Student> students;
}
