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

import java.util.Iterator;

public class DisciplineRegisterController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtWorkload;
    @FXML
    private Button btnCancel;

    @FXML
    private ListView<CheckBox> checkBoxListView;

    private Discipline discipline;
    private ObservableList<CheckBox> disciplines = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        checkBoxListView.setCellFactory(CheckBoxListCell.forListView(new Callback<CheckBox, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CheckBox s) {
                return s.onProperty();
            }
        }));
        checkBoxListView.setItems(disciplines);
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
            if (discipline == null) {
                discipline = new Discipline(code, txtName.getText(),
                        Double.parseDouble(txtWorkload.getText()));
            } else {
                discipline.setCode(code);
                discipline.setName(txtName.getText());
                discipline.setWorkload(Double.parseDouble(txtWorkload.getText()));
            }
            setDependencies(discipline);
            course.addDiscipline(discipline);
            // TODO Msg de Success
            close();
        } else {
            // TODO show msg discipline already registered
        }
    }

    private void setDependencies(Discipline discipline) {
        Iterator<CheckBox> itCheckBox = disciplines.iterator();
        while (itCheckBox.hasNext()) {
            CheckBox checkBox = itCheckBox.next();
            if (checkBox.isOn())
                discipline.addDependency(checkBox.getDiscipline());
        }
    }

    private boolean isFormValid() {
        return true;
    }

    public void setDisciplines(Iterator<Discipline> disciplinesIt) {
        disciplines = FXCollections.observableArrayList();
        while (disciplinesIt.hasNext())
            disciplines.add(new CheckBox(disciplinesIt.next(), false));
        checkBoxListView.setItems(disciplines); // TODO ver com o Lucas pq isso é necessario
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
