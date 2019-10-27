package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.loaders.WindowSubjectsImport;

public class SubjectsViewController {

    @FXML
    private Button btnRegister;

    public void openRegisterModal() {
        WindowSubjectsImport windowSubjectsImport = new WindowSubjectsImport();
        windowSubjectsImport.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.close();
    }

}
