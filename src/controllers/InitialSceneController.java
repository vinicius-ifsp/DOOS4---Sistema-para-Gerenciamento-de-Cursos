package controllers;

import dao.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Course;
import resources.CourseSingleton;
import utils.ListViewPropertyCellFactory;
import views.loaders.WindowDashboard;
import views.loaders.WindowInitialScreenModal;

public class InitialSceneController {
    @FXML private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;
    private CourseDAO courseDAO = new CourseDAO();

    @FXML
    private void initialize() {
        courseListView.setCellFactory(new ListViewPropertyCellFactory<>(Course::getName));
        updateCourseListView();
    }

    private void updateCourseListView() {
        courseObservableList = FXCollections.observableArrayList(courseDAO.findAll());
        courseListView.setItems(courseObservableList);
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
        Course course = new Course();
        WindowInitialScreenModal windowInitialScreenModal = new WindowInitialScreenModal(course);
        windowInitialScreenModal.show();
        if (course.getName() != null) {
            updateCourseListView();
        }
    }
}
