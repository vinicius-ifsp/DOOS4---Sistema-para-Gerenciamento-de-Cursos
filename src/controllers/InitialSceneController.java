package controllers;

import javafx.fxml.FXML;
import views.loaders.WindowDashboard;
import views.loaders.WindowInitialScreenModal;

public class InitialSceneController {
    @FXML
    private void openCourse() {
        WindowDashboard windowDashboard = new WindowDashboard();
        windowDashboard.show();
    }

    @FXML
    private void registerCourse() {
        WindowInitialScreenModal windowInitialScreenModal = new WindowInitialScreenModal();
        windowInitialScreenModal.show();
    }
}
