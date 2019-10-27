package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SubjectsImportController {
    @FXML
    private Button btnCancel;

    @FXML
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void register() {
        close();
    }
}
