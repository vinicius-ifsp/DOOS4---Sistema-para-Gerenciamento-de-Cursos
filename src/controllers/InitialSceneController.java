package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import views.loaders.WindowDashboard;
import views.loaders.WindowInitialScreenModal;

public class InitialSceneController {
    ObservableList<String> items = FXCollections.observableArrayList (
            "Análise e Desenvolimento de Sistemas", "Técnico em Informática", "Sistemas de Computação");
    @FXML private ListView<String> listCourses;

    @FXML void initialize() {
        listCourses.setItems(items);
    }

    @FXML
    private void openCourse() {
        WindowDashboard windowDashboard = new WindowDashboard();
        windowDashboard.show();
    }

    @FXML
    private void registerCourse() {
        WindowInitialScreenModal windowInitialScreenModal = new WindowInitialScreenModal();
        windowInitialScreenModal.show();
    }




    /*public void setCourses(ListView<String> courses, FXCollections items) {
        this.courses = courses;
        courses.setItems((ObservableList<String>) items);
    }*/
}
