package controllers;

import dao.DisciplineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import models.Student;
import resources.CourseSingleton;
import resources.StudentStatus;
import utils.DataLoader;
import utils.ListViewPropertyCellFactory;
import views.loaders.WindowDisciplineRegister;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private DisciplineDAO disciplineDAO = new DisciplineDAO();

    @FXML
    private void initialize() {
        course = CourseSingleton.getInstance().getCourse();
        courseName.setText(course.getName());
        disciplinesList.setCellFactory(new ListViewPropertyCellFactory<>(Discipline::getDescriptionToListView));
        listDisciplineDependencies.setCellFactory(new ListViewPropertyCellFactory<>(Discipline::getDescriptionToListView));
        listLateStudents.setCellFactory(new ListViewPropertyCellFactory<>(Student::getDescriptionToListView));
        listRedStudents.setCellFactory(new ListViewPropertyCellFactory<>(Student::getDescriptionToListView));
        updateDisciplinesList();
        editDiscipline.setVisible(false);
    }

    private void updateDisciplinesList() {
        Iterator<Map.Entry<String, Discipline>> disciplinesIt = course.getDisciplines();
        disciplines = FXCollections.observableArrayList();
        while (disciplinesIt.hasNext())
            disciplines.add(disciplinesIt.next().getValue());
        disciplinesList.setItems(disciplines);
        disciplinesList.refresh();
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

    @FXML
    private void importDisciplines() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File fileDisciplines = fileChooser.showOpenDialog(disciplinesList.getScene().getWindow());

        if (fileDisciplines == null) {
            return;
        }

        try {
            HashMap<String, Discipline> map = DataLoader.loadDisciplines(fileDisciplines);
            disciplineDAO.save(map.entrySet().iterator());
            course.addDiscipline(disciplineDAO.findByCourse(course.getCode()));
            updateDisciplinesList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void selectedDiscipline() {
        Discipline selectedDiscipline = disciplinesList.getSelectionModel().getSelectedItem();
        if (selectedDiscipline != null) {
            formatToShow(selectedDiscipline);
        }
    }

    private void formatToShow(Discipline discipline){
        lDisciplineName.setText(discipline.getName());
        lDisciplineCode.setText(discipline.getCode());
        lDisciplineTime.setText(Double.toString(discipline.getWorkload()));
        lDisciplineDependencies.setText("" + discipline.getQtyDependencies());

        ObservableList<Student> studentObservableList = FXCollections.observableArrayList(course.getStudentLateByDiscipline(discipline));
        listLateStudents.setItems(studentObservableList);
        listLateStudents.refresh();

        lDisciplineLateStudentQtn.setText(""+studentObservableList.size());
        editDiscipline.setVisible(true);

        ObservableList<Student> listRedStudentsObservableList = FXCollections.observableArrayList(course.getStudentsByStatus(StudentStatus.VERMELHO));
        listRedStudents.setItems(listRedStudentsObservableList);
        listRedStudents.refresh();

        ObservableList<Discipline> listDisciplineDependenciesObservableList = FXCollections.observableArrayList(mapToArrayList(discipline.getDependencies()));
        listDisciplineDependencies.setItems(listDisciplineDependenciesObservableList);
        listDisciplineDependencies.refresh();
    }


    private List<Discipline> mapToArrayList(Iterator<Discipline> dependencies) {
        List<Discipline> disciplines = new ArrayList<>();
        while(dependencies.hasNext())
            disciplines.add(dependencies.next());
        return disciplines;
    }
}
