package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import resources.CourseSingleton;
import views.loaders.MockSingleton;
import views.loaders.WindowStudentsView;
import views.loaders.WindowSubjectsView;

public class DashboardController {

    @FXML
    private Button btnDisciplines;

    public DashboardController() {
        // TODO Change by course selected
        if (CourseSingleton.getInstance().getCourse() == null)
            CourseSingleton.getInstance().setCourse(MockSingleton.getInstance().getCourse(1));

    }


    @FXML
    private void openDisciplines() {
        WindowSubjectsView windowSubjectsView = new WindowSubjectsView();
        windowSubjectsView.show();
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