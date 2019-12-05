package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import models.Student;
import resources.CourseSingleton;
import utils.DataLoader;

import java.io.File;
import java.io.IOException;
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
    private TableColumn<Student, String> cPPC;
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
    @FXML Button editStudent;
    @FXML Button disciplinesSuggestionBtn;



    private Course course;
    private ObservableList<Student> studentObservableList;

    @FXML
    private void initialize() {
        cName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cProntuario.setCellValueFactory(new PropertyValueFactory<>("prontuario"));
        cPPC.setCellValueFactory(new PropertyValueFactory<>("ppc"));

        course = CourseSingleton.getInstance().getCourse();
        courseName.setText(course.getName());
        Iterator<Map.Entry<String, Student>> studentsIt = course.getStudents();
        studentObservableList = FXCollections.observableArrayList();
        while (studentsIt.hasNext())
            studentObservableList.add(studentsIt.next().getValue());
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
        if (selectedStudent != null ) {
            // TODO populate panel left
            selectedStudent.calculateTimeToConclusion();
        }
    }

    @FXML
    private void searchStudent() {
        ObservableList<Student> filteredStudents = FXCollections.observableArrayList();
        String name = txtName.getText();
        String prontuario = txtProntuario.getText();
        if (name.equals("") && prontuario.equals("")) {
            filteredStudents = studentObservableList;
        } else {
            Iterator<Student> itStudent = studentObservableList.iterator();
            while (itStudent.hasNext()) {
                Student student = itStudent.next();
                if (student.getNome().toLowerCase().contains(name) || student.getProntuario().equalsIgnoreCase(prontuario))
                    filteredStudents.add(student);
            }
        }

        studentTable.setItems(filteredStudents);
        studentTable.refresh();
    }


    public void importStudents(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File fileStudents = fileChooser.showOpenDialog(studentTable.getScene().getWindow());

        if(fileStudents == null)
            return;

        try{
            List<Student> studentList = DataLoader.loadStudents(fileStudents);
            course.addStudents(studentList);
            studentObservableList.addAll(studentList);
            studentTable.setItems(studentObservableList);
            course.addStudentsClass(DataLoader.loadStudentClasses(null));
        } catch(IOException e){
            throw (e);
        }
    }

    private void formatToShow (Student student){
        editStudent.setVisible(true);
        comentArea.setVisible(false);
        disciplinesSuggestionBtn.setVisible(true);

        lStudentName.setText(student.getNome());
        lStudentNumber.setText(student.getProntuario());
        lStudentEntry.setText(Integer.toString(student.getAnoIngresso()));
        lStudentSemester.setText(Integer.toString(student.getSemAtual()));
        lStudentEstimate.setText("2020");
        lStudentStatus.setText("Ruim");
        comentArea.setText("Muito Bom");
    }

}
