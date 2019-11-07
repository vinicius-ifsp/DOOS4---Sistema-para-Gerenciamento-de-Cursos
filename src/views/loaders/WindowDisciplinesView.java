package views.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowDisciplinesView {

    public void show() {
        FXMLLoader loader = new FXMLLoader();
        Pane sceneGraph;
        try {
            sceneGraph = loader.load(getClass()
                    .getResource("/views/FXML/DisciplinesView.fxml").openStream());
            Scene scene = new Scene(sceneGraph);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
