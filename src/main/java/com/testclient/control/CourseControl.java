package com.testclient.control;

import com.testclient.model.Course;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import lombok.Data;

@Data
public class CourseControl extends Control {
    private Course course;

    public CourseControl(Course course) {
        this.course = course;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CourseSkinBase(this);
    }

    public boolean isCourseSelected() {
        return ((CourseSkinBase) getSkin()).isCourseSelected();
    }
}
