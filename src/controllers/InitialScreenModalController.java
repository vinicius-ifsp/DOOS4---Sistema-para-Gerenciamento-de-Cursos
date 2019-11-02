package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Course;
import views.loaders.MockSingleton;

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

    @FXML
    private void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        System.out.println(MockSingleton.getInstance().toString());
        stage.close();
    }

    @FXML
    private void register() {
        if (!isFormValid()) {
            // TO-DO show msg in invalid fields
            return;
        }

        int code = 3;
        if (!MockSingleton.getInstance().hasCourse(code)) {
            Course course = new Course(code, Integer.parseInt(txtPeriodQty.getText()), txtName.getText(),
                    txtPpc.getText(), Double.parseDouble(txtWorkload.getText()), null);

            MockSingleton.getInstance().addCourse(course);
            System.out.println(course);
            // TO - DO Msg de Success
            close();
        } else {
            // TO-DO show msg course already registered
        }
    }

    private boolean isFormValid() {
        return true;
    }
}
