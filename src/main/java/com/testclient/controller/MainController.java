package com.testclient.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.testclient.control.CourseControl;
import com.testclient.control.CourseSkinBase;
import com.testclient.model.Course;
import com.testclient.model.Student;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    private Student loggedStudent;
    @FXML
    private VBox box;

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }

    public void exitMenuAction() {
        Platform.exit();
    }

    public void registerAction() {
        final List<Course> selectedCourses = getSelectedCourses();
        loggedStudent.setCourses(selectedCourses);

        Client client = buildClient();
        final ClientResponse response = client.resource("http://localhost:8080/test-task/rest/students")
                .type("application/json")
                .post(ClientResponse.class, loggedStudent);

        switch (response.getStatus()) {
            case 200:
                new Alert(Alert.AlertType.INFORMATION, response.getEntity(String.class)).show();
                break;
            case 500:
                new Alert(Alert.AlertType.ERROR, response.getEntity(String.class)).show();
                break;
            default:
                break;
        }
    }

    private List<Course> getSelectedCourses() {
        List<Course> selectedCourses = box.getChildren().stream()
                .map(CourseControl.class::cast)
                .filter(CourseControl::isCourseSelected)
                .map(CourseControl::getCourse)
                .collect(Collectors.toList());

        return selectedCourses;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client client = buildClient();

        GenericType<List<Course>> genericType = new GenericType<List<Course>>() {

        };
        final ClientResponse response = client
                .resource("http://localhost:8080/test-task/rest/courses")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        List<Course> courses = response.getEntity(genericType);
        box.getChildren().setAll(courses.stream().map(CourseControl::new).collect(Collectors.toList()));
    }

    private Client buildClient() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        return Client.create(config);
    }
}
