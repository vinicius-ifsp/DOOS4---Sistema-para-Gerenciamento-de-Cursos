package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Course;
import models.Discipline;
import resources.CourseSingleton;
import views.loaders.MockSingleton;
import views.loaders.WindowDisciplineRegister;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InitialScreenModalController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPpc;
    @FXML
    private TextField txtPeriodQty;
    @FXML
    private TextField txtWorkload;
    @FXML
    private Button btnClose;

    private Course course = new Course();

    @FXML
    private void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        System.out.println(MockSingleton.getInstance().toString());
        stage.close();
    }

    @FXML
    private void register() {
        if (!isFormValid()) {
            // TODO show msg in invalid fields
            return;
        }

        int code = MockSingleton.getInstance().generateCode();
        if (!MockSingleton.getInstance().hasCourse(code)) {
            course.setCode(code);
            course.setPeriodQty(Integer.parseInt(txtPeriodQty.getText()));
            course.setName(txtName.getText());
            course.setPpc(txtPpc.getText());
            course.setWorkload(Double.parseDouble(txtWorkload.getText()));

            MockSingleton.getInstance().addCourse(course);
            System.out.println(course);
            // TODO Msg de Success
            close();
        } else {
            // TODO show msg course already registered
        }
    }

    @FXML
    private void openWindowAddDiscipline() {
        CourseSingleton.getInstance().setCourse(course);
        WindowDisciplineRegister windowDisciplineRegister = new WindowDisciplineRegister(null, getDisciplinesExists());
        windowDisciplineRegister.show();
    }

    private boolean isFormValid() {
        return true;
    }

    private Iterator<Discipline> getDisciplinesExists() {
        List<Discipline> disciplines = new ArrayList<>();
        Iterator<Map.Entry<String, Discipline>> disciplinesIt = course.getDisciplines();
        while (disciplinesIt.hasNext())
            disciplines.add(disciplinesIt.next().getValue());
        return disciplines.iterator();
    }
}
