package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Course;
import views.loaders.WindowSubjectsImport;

public class SubjectsViewController {
    ObservableList<String> items = FXCollections.observableArrayList (
            "Programação e Lógica 1", "Inglês", "Programação Orientada a Objetos", "Redes 2");
    @FXML private ListView<String> subjectsList;

    @FXML void initialize() {
        subjectsList.setItems(items);
    }
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
