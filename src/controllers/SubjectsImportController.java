package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SubjectsImportController {
    @FXML
    Button btnCancel;

    public void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
