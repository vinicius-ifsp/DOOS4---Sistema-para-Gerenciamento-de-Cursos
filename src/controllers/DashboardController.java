package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Course;
import resources.CourseSingleton;
import views.loaders.WindowStudentsView;
import views.loaders.WindowDisciplinesView;

public class DashboardController {
    @FXML
    private Button btnDisciplines;

    @FXML
    private Label FirstReproofDiscipline;
    @FXML
    private Label SecondReproofDiscipline;
    @FXML
    private Label ThirdReproofDiscipline;
    @FXML
    private Label FirstReproofDisciplineDependents;
    @FXML
    private Label SecondReproofDisciplineDependents;
    @FXML
    private Label ThirdReproofDisciplineDependents;
    @FXML
    private Label courseName;

    private Course course;

    @FXML
    private void initialize() {
        // TODO load courses statistics
        course = CourseSingleton.getInstance().getCourse();
        formatToShow();
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

    private void formatToShow() {
        courseName.setText(course.getName());
        FirstReproofDiscipline.setText("Nome da Disciplina");
        FirstReproofDisciplineDependents.setText("3");

        SecondReproofDiscipline.setText("Nome da Disciplina");
        SecondReproofDisciplineDependents.setText("2");

        ThirdReproofDiscipline.setText("Nome da Disciplina");
        ThirdReproofDisciplineDependents.setText("2");
    }
}