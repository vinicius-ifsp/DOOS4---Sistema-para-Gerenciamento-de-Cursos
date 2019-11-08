package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CheckBox {

    private Discipline discipline;
    private final BooleanProperty on = new SimpleBooleanProperty();

    public CheckBox(Discipline discipline, boolean on){
        this.discipline = discipline;
        setOn(on);
    }

    public final BooleanProperty onProperty() {
        return this.on;
    }

    public final boolean isOn() {
        return this.onProperty().get();
    }

    public final void setOn(final boolean on) {
        this.onProperty().set(on);
    }

}
