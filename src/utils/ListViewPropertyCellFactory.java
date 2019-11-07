package utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.function.Function;

public class ListViewPropertyCellFactory<T> implements Callback<ListView<T>, ListCell<T>> {

    private final Function<T, String> property ;

    public ListViewPropertyCellFactory(Function<T, String> property) {
        this.property = property ;
    }

    @Override
    public ListCell<T> call(ListView<T> listView) {
        return new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? null : property.apply(item));
            }
        };
    }
}