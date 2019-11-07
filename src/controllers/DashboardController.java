package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.loaders.WindowStudentsView;
import views.loaders.WindowDisciplinesView;

public class DashboardController {
    @FXML
    private Button btnDisciplines;

    @FXML
    private void initialize() {
        // TODO load courses statistics
    }


    @FXML
    private void openDisciplines() {
        WindowDisciplinesView windowDisciplinesView = new WindowDisciplinesView();
        windowDisciplinesView.show();
    }

    @FXML
    private void openStudents() {
        WindowStudentsView windowStudentsView = new WindowStudentsView();
        windowStudentsView.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) btnDisciplines.getScene().getWindow();
        stage.close();
    }
}