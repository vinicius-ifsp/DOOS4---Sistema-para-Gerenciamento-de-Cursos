package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import resources.CourseSingleton;
import utils.ListViewPropertyCellFactory;
import views.loaders.WindowDisciplineRegister;

import java.util.Iterator;
import java.util.Map;

public class DisciplinesViewController {
    @FXML private ListView<Discipline> disciplinesList;

    private Course course;
    private ObservableList<Discipline> disciplines;

    @FXML
    private void initialize() {
        course = CourseSingleton.getInstance().getCourse();
        Iterator<Map.Entry<String, Discipline>> disciplinesIt = course.getDisciplines();
        disciplines = FXCollections.observableArrayList();
        while (disciplinesIt.hasNext())
            disciplines.add(disciplinesIt.next().getValue());
        disciplinesList.setItems(disciplines);
        disciplinesList.setCellFactory(new ListViewPropertyCellFactory<>(Discipline::getName));
    }


    @FXML
    private void openRegisterModal() {
        WindowDisciplineRegister windowDisciplineRegister = new WindowDisciplineRegister(disciplines.iterator());
        windowDisciplineRegister.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) disciplinesList.getScene().getWindow();
        stage.close();
    }
}
