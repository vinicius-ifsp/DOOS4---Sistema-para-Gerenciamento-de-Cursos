package controllers;

import dao.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import models.Student;
import models.StudentRemainingDiscipline;
import resources.CourseSingleton;
import resources.StudentStatus;
import utils.DataLoader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StudentsViewController {

    @FXML
    private Button importStudentsButton;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> cName;
    @FXML
    private TableColumn<Student, String> cProntuario;
    @FXML
    private TableColumn<Student, String> cStatus;
    @FXML
    private Label courseName;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtProntuario;
    @FXML
    private Label lStudentName;
    @FXML
    private Label lStudentNumber;
    @FXML
    private Label lStudentEntry;
    @FXML
    private Label lStudentSemester;
    @FXML
    private Label lStudentStatus;
    @FXML
    private Label lStudentEstimate;
    @FXML
    private ListView<Discipline> listAttendedDisciplines;
    @FXML
    private ListView<Discipline> listStudentLateDisciplines;
    @FXML
    private TextArea comentArea;
    @FXML
    private Button editStudent;
    @FXML
    private Button disciplinesSuggestionBtn;


    private Course course;
    private ObservableList<Student> studentObservableList;
    private StudentDAO studentDAO = new StudentDAO();

    @FXML
    private void initialize() {
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cProntuario.setCellValueFactory(new PropertyValueFactory<>("prontuario"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        course = CourseSingleton.getInstance().getCourse();
        courseName.setText(course.getName());

        List<Student> studentList = getStudentArrayList(course.getStudents());
        studentObservableList = FXCollections.observableArrayList(studentList);
        studentTable.setItems(studentObservableList);


        editStudent.setVisible(false);
        comentArea.setVisible(false);
        disciplinesSuggestionBtn.setVisible(false);
    }

    @FXML
    private void close() {
        Stage stage = (Stage) importStudentsButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selectedStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            int timeToConclusion = selectedStudent.getTimeToConclusion();
            int timeToConclusionYears = timeToConclusion / 2;
            String estimateTimeToConclusion;

            if (timeToConclusion % 2 != 0) {
                if (LocalDateTime.now().getMonthValue() > 6) {
                    estimateTimeToConclusion = (LocalDateTime.now().getYear() + timeToConclusionYears + 1) + "/1";
                } else {
                    estimateTimeToConclusion = (LocalDateTime.now().getYear() + timeToConclusionYears) + "/2";
                }
            } else {
                estimateTimeToConclusion = (LocalDateTime.now().getYear() + timeToConclusionYears) + "/" +
                        (LocalDateTime.now().getMonthValue() > 6 ? 2 : 1);
            }


            editStudent.setVisible(true);
            comentArea.setVisible(false);
            disciplinesSuggestionBtn.setVisible(true);

            lStudentName.setText(selectedStudent.getNome());
            lStudentNumber.setText(selectedStudent.getProntuario());
            lStudentEntry.setText(Integer.toString(selectedStudent.getAnoIngresso()));
            lStudentSemester.setText(Integer.toString(selectedStudent.getSemAtual()));
            lStudentEstimate.setText(estimateTimeToConclusion);
            lStudentStatus.setText(selectedStudent.getStatus().toString());
            comentArea.setText("Muito Bom");

        }
    }

    @FXML
    private void filterStudents(MouseEvent mouseEvent) {
        ObservableList<Student> filteredStudents = FXCollections.observableArrayList();
        String name = txtName.getText();
        String prontuario = txtProntuario.getText();

        if (name.equals("") && prontuario.equals("")) {
            filteredStudents = studentObservableList;
        } else {
            Iterator<Student> itStudent = studentObservableList.iterator();
            while (itStudent.hasNext()) {
                Student student = itStudent.next();
                if (student.getNome().toLowerCase().contains(name)) {
                    if (prontuario.equals("") || student.getProntuario().equals(prontuario))
                        filteredStudents.add(student);
                }
            }
        }

        studentTable.setItems(filteredStudents);
        studentTable.refresh();
    }

    @FXML
    private void filterByStatus(MouseEvent mouseEvent) {
        String id = ((Control) mouseEvent.getSource()).getId();
        StudentStatus studentStatus = null;
        switch (id) {
            case "redButton":
                studentStatus = StudentStatus.VERMELHO;
                break;
            case "yellowButton":
                studentStatus = StudentStatus.AMARELO;
                break;
            case "greenButton":
                studentStatus = StudentStatus.VERDE;
                break;
        }
        ObservableList<Student> filteredStudents = FXCollections.observableArrayList();
        Iterator<Student> itStudent = studentObservableList.iterator();
        while (itStudent.hasNext()) {
            Student student = itStudent.next();
            if (student.getStatus() == studentStatus)
                filteredStudents.add(student);
        }
        studentTable.setItems(filteredStudents);
        studentTable.refresh();
    }

    public void importStudents(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File fileStudents = fileChooser.showOpenDialog(studentTable.getScene().getWindow());

        if (fileStudents == null)
            return;

        try {
            List<Student> studentList = DataLoader.loadStudents(fileStudents);
            studentDAO.saveStudentList(studentList);
            course.addStudents(studentList);

            fileStudents = fileChooser.showOpenDialog(studentTable.getScene().getWindow());
            List<StudentRemainingDiscipline> studentRemainingDisciplines = DataLoader.loadStudentRemainingDisciplines(fileStudents);
            studentDAO.saveStudentRemainingDisciplines(studentRemainingDisciplines);
            course.addRemainingDisciplines(studentRemainingDisciplines);

            studentList = getStudentArrayList(course.getStudents());
            studentObservableList = FXCollections.observableArrayList(studentList);
            studentTable.setItems(studentObservableList);
            studentTable.refresh();
        } catch (IOException e) {
            throw (e);
        }
    }

    private List<Student> getStudentArrayList(Iterator<Map.Entry<String, Student>> studentsIt) {
        List<Student> students = new ArrayList<>();
        while (studentsIt.hasNext())
            students.add(studentsIt.next().getValue());
        return students;
    }
}
