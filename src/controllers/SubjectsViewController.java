package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Course;
import views.loaders.WindowSubjectsImport;

public class SubjectsViewController {

    @FXML
    private Button btnRegister;

    private Course course;

    @FXML
    private void openRegisterModal() {
        WindowSubjectsImport windowSubjectsImport = new WindowSubjectsImport();
        windowSubjectsImport.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.close();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
