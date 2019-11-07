package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Course;
import views.loaders.MockSingleton;
import views.loaders.WindowDashboard;
import views.loaders.WindowInitialScreenModal;

import java.util.Iterator;

public class InitialSceneController {

    private ObservableList<Course> courses;

    @FXML
    private void initialize() {
        //TODO Change listView
        ListView listView = new ListView();
        Iterator<Course> coursesIt = MockSingleton.getInstance().getCourses();
        courses = FXCollections.observableArrayList();
        while(coursesIt.hasNext())
            courses.add(coursesIt.next());
        listView.setItems(courses);
    }

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
