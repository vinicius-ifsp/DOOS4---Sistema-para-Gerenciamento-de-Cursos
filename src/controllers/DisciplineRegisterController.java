package controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.CheckBox;
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
    private ListView<CheckBox> listCourses;


    @FXML void initialize(){
        CheckBox ap1 = new CheckBox(new Discipline("1", "Algoritmos e Programação I", 100.00), false);
        CheckBox ap2 = new CheckBox(new Discipline("2", "Algoritmos e Programação II", 100.00), false);
        CheckBox bd1 = new CheckBox(new Discipline("3", "Banco de Dados I", 66.66), false);
        CheckBox bd2 = new CheckBox(new Discipline("4", "Banco de Dados II", 66.66), false);
        CheckBox poo = new CheckBox(new Discipline("5", "Programação Orientada a Objetos", 100.00), false);
        CheckBox doo = new CheckBox(new Discipline("6", "Desenvolvimento Orientado a Objetos", 100.00), false);

        ObservableList<CheckBox> items = FXCollections.observableArrayList (ap1, ap2, poo, doo, bd1, bd2);

        listCourses.setCellFactory(CheckBoxListCell.forListView(new Callback<CheckBox, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CheckBox s) {
                return s.onProperty();
            }
        }));
    }
  
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
