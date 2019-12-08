package controllers;

import dao.CourseDAO;
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

    private Course course;
    private CourseDAO courseDAO = new CourseDAO();

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

        course.setPeriodQty(Integer.parseInt(txtPeriodQty.getText()));
        course.setName(txtName.getText());
        course.setPpc(txtPpc.getText());
        course.setWorkload(Double.parseDouble(txtWorkload.getText()));

        if (courseDAO.save(course)) {
            System.out.println(course);
            close();
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

    public void setCourse(Course course) {
        this.course = course;
    }
}
