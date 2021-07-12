package com.testclient.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.testclient.auth.AuthRequest;
import com.testclient.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField loginText;

    private WebResource.Builder builder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final DefaultClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        this.builder = client.resource("http://localhost:8080/test-task/auth/login")
                .type("application/json");
    }


    @SneakyThrows
    public void loginAction() {
        final ClientResponse response = builder.post(ClientResponse.class,
                new AuthRequest(loginText.getText(), passwordText.getText()));

        if (response.getStatus() != 200) {
            errorLabel.setText(response.getEntity(String.class));
            return;
        }

        Student loggedStudent = response.getEntity(Student.class);
        showMainWindow(loggedStudent);
    }

    private void showMainWindow(Student loggedStudent) throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/main.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Регистрация курсов");
        stage.setScene(new Scene(root));

        ((MainController) loader.getController()).setLoggedStudent(loggedStudent);
        stage.show();
    }
}
