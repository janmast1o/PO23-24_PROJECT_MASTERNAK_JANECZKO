package presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Position;
import model.WorldMap;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.TRANSPARENT;

public class UIMapListener implements MapChangeListener{

    @FXML
    GridPane mapGrid;

    public synchronized void mapChanged (WorldMap map, Position position) {
        Platform.runLater(()-> {
            drawGrid(map);
        });
    }

    private void clearGrid () {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void drawGrid (WorldMap map) {
        clearGrid();
        int a = map.getBoundaries().lowerLeft().x();
        int b = map.getBoundaries().lowerLeft().y();
        int c = map.getBoundaries().upperRight().x();
        int d = map.getBoundaries().upperRight().y();

        for (int i = a; i <= c; i++) {
            for (int j = b; j <= d; j++) {
                Rectangle cell = new Rectangle (25,25);
                cell.setStroke(BLACK);
                cell.setFill(TRANSPARENT);
                Text cellText = new Text("");
                cellText.setText (map.objectAtToString(new Position (i,j)));

                StackPane cellPane = new StackPane();
                cellPane.getChildren().addAll(cell,cellText);

                GridPane.setRowIndex(cellPane,i);
                GridPane.setColumnIndex(cellPane,j);

                mapGrid.getChildren().add(cellPane);
            }
        }
    }
}
