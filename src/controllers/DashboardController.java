package controllers;

import dao.DisciplineDAO;
import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import models.Student;
import models.StudentRemainingDiscipline;
import resources.CourseSingleton;
import resources.StudentStatus;
import views.loaders.WindowDisciplinesView;
import views.loaders.WindowStudentsView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @FXML
    private TableView<Student> yellowStatusTable;
    @FXML
    private TableView<Student> wontGraduateTable;

    @FXML
    private TableColumn<Student, String> cYellowStatusName;
    @FXML
    private TableColumn<Student, String> cYellowStatusPront;
    @FXML
    private TableColumn<Student, String> cWontGraduateName;
    @FXML
    private TableColumn<Student, String> cWontGraduatePront;
    @FXML
    private TableColumn<Student, String> cWontGraduateTime;

    private Course course;
    private DisciplineDAO disciplineDAO = new DisciplineDAO();
    private StudentDAO studentDAO = new StudentDAO();


    private ObservableList<Student> yellowStatusObservableList;
    private ObservableList<Student> wontGraduateObservableList;

    @FXML
    private void initialize() {
        course = CourseSingleton.getInstance().getCourse();
        loadStudentsAndDisciplinesFromDB();
        formatToShow();
    }

    private void loadStudentsAndDisciplinesFromDB() {
        List<Discipline> disciplinesList = disciplineDAO.findByCourse(course.getCode());
        for (Discipline d : disciplinesList)
            course.addDiscipline(d);
        List<Student> students = studentDAO.findByCourse(course.getCode());
        for (Student student : students)
            course.addStudent(student);
        List<StudentRemainingDiscipline> studentRemainingDisciplines = disciplineDAO.findAllStudentRemainingDiscipline();
        course.addRemainingDisciplines(studentRemainingDisciplines);
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

        cYellowStatusName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cYellowStatusPront.setCellValueFactory(new PropertyValueFactory<>("prontuario"));

        cWontGraduateName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cWontGraduatePront.setCellValueFactory(new PropertyValueFactory<>("prontuario"));
        cWontGraduateTime.setCellValueFactory(new PropertyValueFactory<>("timeToConclusion"));


        List<Student> yellowStatusStudents = getStudentWithYellowStatusArrayList(course.getStudents());
        yellowStatusObservableList = FXCollections.observableList(yellowStatusStudents);
        yellowStatusTable.setItems(yellowStatusObservableList);


        List<Student> wontGraduateStudents = getNotGraduatingStudentsArrayList(course.getStudents());
        wontGraduateObservableList = FXCollections.observableList(wontGraduateStudents);
        wontGraduateTable.setItems(wontGraduateObservableList);


        courseName.setText(course.getName());
        Map<String, Double> disciplinesWithMostReproof = disciplineDAO.findDisciplinesWithMostReproof();
        int count = 0;

        for (String s : disciplinesWithMostReproof.keySet()) {
            if (count > 2)
                break;
            if (count == 0) {
                FirstReproofDiscipline.setText(s);
                FirstReproofDisciplineDependents.setText(disciplinesWithMostReproof.get(s).toString());
            } else if (count == 1) {
                SecondReproofDiscipline.setText(s);
                SecondReproofDisciplineDependents.setText(disciplinesWithMostReproof.get(s).toString());
            } else {
                ThirdReproofDiscipline.setText(s);
                ThirdReproofDisciplineDependents.setText(disciplinesWithMostReproof.get(s).toString());
            }
            count++;
        }

    }

    private List<Student> getStudentWithYellowStatusArrayList(Iterator<Map.Entry<String, Student>> studentsIt) {
        List<Student> students = new ArrayList<>();
        while (studentsIt.hasNext()) {
            Student student = studentsIt.next().getValue();
            if (student.getStatus() == StudentStatus.AMARELO) {
                students.add(student);
            }
        }
        return students;
    }


    private List<Student> getNotGraduatingStudentsArrayList(Iterator<Map.Entry<String, Student>> studentsIt) {
        List<Student> students = new ArrayList<>();
        while (studentsIt.hasNext()) {
            Student student = studentsIt.next().getValue();
            if (student.getTimeToConclusion() > 5) {
                students.add(student);
            }
        }
        return students;
    }
}