package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Course;
import models.Student;
import resources.CourseSingleton;

public class StudentRegistrationModalController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtProntuario;
    @FXML
    private TextField txtIngressYear;
    @FXML
    private TextField txtIngressSemester;
    @FXML
    private TextField txtCurrentSemester;
    @FXML
    private Button btnClose;

    @FXML
    private void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void register() {
        if (!isFormValid()) {
            // TODO show msg in invalid fields
            return;
        }

        Course course = CourseSingleton.getInstance().getCourse();
        if (!course.hasStudent(txtProntuario.getText())) {
            course.addStudent(null);

            // TODO Msg de Success
            System.out.println(course);
            close();
        } else {
            // TODO show msg student already registered
        }
    }

    private boolean isFormValid() {
        return true;
    }
}
