package com.testclient.control;

import com.testclient.model.Course;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import lombok.Data;

public class CourseSkinBase extends SkinBase<CourseControl> {
    private HBox holderBox;
    private Label courseNameLabel;
    private Label courseWorkLoadLabel;
    private CheckBox checkBox;
    private final Font font = new Font(16);
    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected CourseSkinBase(CourseControl control) {
        super(control);
        Course course = getSkinnable().getCourse();

        holderBox = new HBox();
        holderBox.setSpacing(3);
        holderBox.setAlignment(Pos.BASELINE_CENTER);

        courseNameLabel = new Label(course.getCourseName());
        courseWorkLoadLabel = new Label(course.getWorkLoad().toString().concat(" ч."));
        checkBox = new CheckBox("Подписаться");

        courseNameLabel.setFont(font);
        courseWorkLoadLabel.setFont(font);
        checkBox.setFont(font);

        courseNameLabel.setPrefWidth(300);
        courseWorkLoadLabel.setPrefWidth(100);

        holderBox.getChildren().add(courseNameLabel);
        holderBox.getChildren().add(courseWorkLoadLabel);
        holderBox.getChildren().add(checkBox);

        getChildren().setAll(holderBox);
    }

    public boolean isCourseSelected() {
        return checkBox.isSelected();
    }
}
