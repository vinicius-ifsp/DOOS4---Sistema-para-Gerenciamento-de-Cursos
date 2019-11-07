package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Course;
import models.Student;
import resources.CourseSingleton;
import views.loaders.WindowStudentRegistrationModal;

import java.util.Iterator;
import java.util.Map;

public class StudentsViewController {

    @FXML
    private Button importStudentsButton;

    private Course course;
    private ObservableList<Student> students;

    @FXML
    private void initialize() {
        //TODO Change listView
        ListView listView = new ListView();
        course = CourseSingleton.getInstance().getCourse();
        Iterator<Map.Entry<String, Student>> studentsIt = course.getStudents();
        students = FXCollections.observableArrayList();
        while (studentsIt.hasNext())
            students.add(studentsIt.next().getValue());
        listView.setItems(students);
    }

    @FXML
    private void openRegisterModal() {
        WindowStudentRegistrationModal windowStudentRegistrationModal = new WindowStudentRegistrationModal();
        windowStudentRegistrationModal.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) importStudentsButton.getScene().getWindow();
        stage.close();
    }

}
