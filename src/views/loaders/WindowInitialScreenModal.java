package views.loaders;

import controllers.InitialScreenModalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Course;

public class WindowInitialScreenModal {
    private Course course;

    public WindowInitialScreenModal(Course course) {
        this.course = course;
    }

    public void show() {
        FXMLLoader loader = new FXMLLoader();
        Pane sceneGraph;
        try {
            sceneGraph = loader.load(getClass()
                    .getResource("/views/FXML/InitialScreenModal.fxml").openStream());
            Scene scene = new Scene(sceneGraph);
            Stage stage = new Stage();

            InitialScreenModalController sceneCtrl = loader.getController();
            sceneCtrl.setCourse(course);

            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
