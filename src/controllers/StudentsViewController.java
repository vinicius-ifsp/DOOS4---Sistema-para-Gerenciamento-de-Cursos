package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.loaders.WindowStudentRegistrationModal;
import views.loaders.WindowSubjectsImport;

public class StudentsViewController {

    @FXML
    private Button btnRegister;

    @FXML
    private void openRegisterModal() {
        WindowStudentRegistrationModal windowStudentRegistrationModal = new WindowStudentRegistrationModal();
        windowStudentRegistrationModal.show();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.close();
    }

}
