package controllers;

import dao.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Course;
import resources.CourseSingleton;
import utils.ListViewPropertyCellFactory;
import views.loaders.MockSingleton;
import views.loaders.WindowDashboard;
import views.loaders.WindowInitialScreenModal;

import java.util.Iterator;

public class InitialSceneController {
    @FXML private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;
    private CourseDAO courseDAO = new CourseDAO();

    @FXML
    private void initialize() {

//        Iterator<Course> coursesIt = MockSingleton.getInstance().getCourses();
        courseObservableList = FXCollections.observableArrayList(courseDAO.findAll());
//        while(coursesIt.hasNext())
//            courseObservableList.add(coursesIt.next());
        courseListView.setItems(courseObservableList);
        courseListView.setCellFactory(new ListViewPropertyCellFactory<>(Course::getName));
    }

    @FXML
    private void openCourse() {
        Course selectedCourse = courseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {

            CourseSingleton.getInstance().setCourse(selectedCourse);
            WindowDashboard windowDashboard = new WindowDashboard();
            windowDashboard.show();
        }
    }

    @FXML
    private void registerCourse() {
        WindowInitialScreenModal windowInitialScreenModal = new WindowInitialScreenModal();
        windowInitialScreenModal.show();
    }
}
