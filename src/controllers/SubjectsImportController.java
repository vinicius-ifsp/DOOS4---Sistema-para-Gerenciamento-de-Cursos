package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import views.loaders.MockSingleton;

public class SubjectsImportController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtWorkload;
    @FXML
    private Button btnCancel;

    @FXML
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        System.out.println(MockSingleton.getInstance().toString());
        stage.close();
    }

    @FXML
    private void register() {
        if (!isFormValid()) {
            // TO-DO show msg in invalid fields
            return;
        }

        // TO-DO Change MockSingleton to class dao
        Course course = MockSingleton.getInstance().getCourse(1);
        String code = txtCode.getText();
        if (!course.hasDiscipline(code)) {
            Discipline discipline = new Discipline(code, txtName.getText(),
                    Double.parseDouble(txtWorkload.getText()));
            System.out.println(discipline);
            System.out.println("Course name: " + course.getName());
             course.addDiscipline(discipline);

            // TO - DO Msg de Success
            close();
        } else {
            // TO-DO show msg discipline already registered
        }

    }

    private boolean isFormValid() {
        return true;
    }
}
