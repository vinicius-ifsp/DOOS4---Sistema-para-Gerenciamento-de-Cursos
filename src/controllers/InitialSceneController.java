package controllers;

import views.loaders.WindowDashboard;

public class InitialSceneController {

    public void openCourse() {
        WindowDashboard windowDashboard = new WindowDashboard();
        windowDashboard.show();
    }
}
