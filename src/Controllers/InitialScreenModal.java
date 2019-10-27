package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitialScreenModal {

    @FXML
    private Button btnClose;

    @FXML
    private void close() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void register() {
        close();
    }
}
