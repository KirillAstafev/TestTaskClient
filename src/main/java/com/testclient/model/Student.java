package com.testclient.model;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long studentId;
    private String studentLogin;
    private List<Course> courses;
}
