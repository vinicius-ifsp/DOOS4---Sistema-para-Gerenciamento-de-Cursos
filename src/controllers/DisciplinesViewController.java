package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import models.Student;
import resources.CourseSingleton;
import utils.ListViewPropertyCellFactory;
import views.loaders.WindowDisciplineRegister;

import java.util.Iterator;
import java.util.Map;

public class DisciplinesViewController {
    @FXML
    private ListView<Discipline> disciplinesList;
    @FXML
    private Label courseName;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private Label lDisciplineName;
    @FXML
    private Label lDisciplineTime;
    @FXML
    private Label lDisciplineCode;
    @FXML
    private Label lDisciplineLateStudentQtn;
    @FXML
    private Label lDisciplineDependencies;
    @FXML
    private Button editDiscipline;
    @FXML
    private ListView<Discipline> listDisciplineDependencies;
    @FXML
    private ListView<Student> listLateStudents;
    @FXML
    private ListView<Student> listRedStudents;

    private Course course;
    private ObservableList<Discipline> disciplines;

    @FXML
    private void initialize() {
        course = CourseSingleton.getInstance().getCourse();
        courseName.setText(course.getName());
        Iterator<Map.Entry<String, Discipline>> disciplinesIt = course.getDisciplines();

        disciplines = FXCollections.observableArrayList();
        while (disciplinesIt.hasNext())
            disciplines.add(disciplinesIt.next().getValue());
        disciplinesList.setItems(disciplines);
        disciplinesList.setCellFactory(new ListViewPropertyCellFactory<>(Discipline::getName));

        editDiscipline.setVisible(false);
    }


    @FXML
    private void openRegisterModal() {
        Discipline discipline = new Discipline();
        WindowDisciplineRegister windowDisciplineRegister = new WindowDisciplineRegister(discipline, disciplines.iterator());
        windowDisciplineRegister.show();

        if (discipline.getCode() != null)
            disciplines.add(discipline);
    }

    @FXML
    private void close() {
        Stage stage = (Stage) disciplinesList.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void searchStudent() {
        ObservableList<Discipline> filteredDisciplines = FXCollections.observableArrayList();
        String name = txtName.getText().toLowerCase();
        String code = txtCode.getText();
        if (name.equals("") && code.equals("")) {
            filteredDisciplines = disciplines;
        } else {
            Iterator<Discipline> itDiscipline = disciplines.iterator();
            while (itDiscipline.hasNext()) {
                Discipline discipline = itDiscipline.next();
                if (discipline.getName().toLowerCase().contains(name) || discipline.getCode().equalsIgnoreCase(code))
                    filteredDisciplines.add(discipline);
            }
        }

        disciplinesList.setItems(filteredDisciplines);
        disciplinesList.refresh();
    }

    private void formatToShow(Discipline discipline){
        lDisciplineName.setText(discipline.getName());
        lDisciplineCode.setText(discipline.getCode());
        lDisciplineTime.setText(Double.toString(discipline.getWorkload()));
        lDisciplineDependencies.setText("3");
        lDisciplineLateStudentQtn.setText("40");
        editDiscipline.setVisible(true);
    }
}
