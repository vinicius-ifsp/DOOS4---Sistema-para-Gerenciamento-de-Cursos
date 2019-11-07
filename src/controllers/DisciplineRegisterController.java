package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import resources.CourseSingleton;
import views.loaders.MockSingleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisciplineRegisterController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtWorkload;
    @FXML
    private Button btnCancel;

    private List<Discipline> disciplines;

    @FXML
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        System.out.println(MockSingleton.getInstance().toString());
        stage.close();
    }

    @FXML
    private void register() {
        if (!isFormValid()) {
            // TODO show msg in invalid fields
            return;
        }

        Course course = CourseSingleton.getInstance().getCourse();
        String code = txtCode.getText();
        if (!course.hasDiscipline(code)) {
            Discipline discipline = new Discipline(code, txtName.getText(),
                    Double.parseDouble(txtWorkload.getText()));
            System.out.println(discipline);
            System.out.println("Course name: " + course.getName());

            course.addDiscipline(discipline);
            // TODO Msg de Success
            close();
        } else {
            // TODO show msg discipline already registered
        }

    }

    private boolean isFormValid() {
        return true;
    }

    public void setDisciplines(Iterator<Discipline> disciplinesIt) {
        disciplines = new ArrayList<>();
        while (disciplinesIt.hasNext())
            disciplines.add(disciplinesIt.next());
    }
}
