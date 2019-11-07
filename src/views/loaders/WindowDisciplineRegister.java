package views.loaders;

import controllers.DisciplineRegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Discipline;

import java.util.Iterator;

public class WindowDisciplineRegister {
    private Iterator<Discipline> disciplinesIt;

    public WindowDisciplineRegister(Iterator<Discipline> disciplinesIt) {
        this.disciplinesIt = disciplinesIt;
    }

    public void show(){
        FXMLLoader loader = new FXMLLoader();
        Pane sceneGraph;
        try {
            sceneGraph = loader.load(getClass()
                    .getResource("/views/FXML/DisciplinesRegister.fxml").openStream());
            Scene scene = new Scene(sceneGraph);
            Stage stage = new Stage();

            DisciplineRegisterController sceneCtrl = loader.getController();
            sceneCtrl.setDisciplines(disciplinesIt);

            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
