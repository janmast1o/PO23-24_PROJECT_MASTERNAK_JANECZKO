package presenter;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Position;
import model.WorldMap;
import simulation.Simulation;
import util.GridCellWidthCalculation;
import util.Parser;
import util.SimulationFileWriter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import static javafx.scene.paint.Color.*;

public class SimulationPresenter {

    @FXML
    private Label dayCounter;

    @FXML
    private GridPane mapGrid;

    @FXML
    private Label numberOfAliveAnimals;

    @FXML
    private Label numberOfPlants;

    @FXML
    private Label numberOfUnoccupiedFields;

    @FXML
    private Label mostPopularGene;

    @FXML
    private Label averageEnergy;

    @FXML
    private Label averageLifetime;

    @FXML
    private Label averageNumberOfChildren;

    @FXML
    private TextField animalTrackerXCoord;

    @FXML
    private TextField animalTrackerYCoord;

    @FXML
    private Label simulationErrorMessage;

    @FXML
    private Label animalPosition;

    @FXML
    private Label animalGenome;

    @FXML
    private Label activeIndex;

    @FXML
    private Label energy;

    @FXML
    private Label numberOfEatenPlants;

    @FXML
    private Label numberOfChildren;

    @FXML
    private Label animalLifetime;

    @FXML
    private Label animalDeathDate;

    @FXML
    private Label animalDescendants;

    private Simulation simulation;

    private Thread simulationThread;

    private String saveFileName;

    private boolean started = false;

    private boolean everInterrupted = false;


    public void setSimulation (Simulation simulation) {
        this.simulation = simulation;
    }

    public void setSaveFileName (String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public void assignANewListenerAndObserverToSimulation() {
        UIMapListenerAndObserver uiMapListener = new UIMapListenerAndObserver(this);
        simulation.setMapChangeListener(uiMapListener);
        simulation.setSimulationObserver(uiMapListener);
    }

    private void clearGrid () {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void drawBorder (int a, int b, int c, int d) {
        double gridCellWidth = GridCellWidthCalculation.determineGridCellWidth (Math.max(c-a+1,d-b+1));
        for (int i = a; i <= c; i++) {
            Rectangle cell = new Rectangle (gridCellWidth,gridCellWidth);
            cell.setStroke(BLACK);
            cell.setFill(GREY);
            Text cellText = new Text(i+"");

            StackPane cellPane = new StackPane();
            cellPane.getChildren().addAll(cell,cellText);

            GridPane.setRowIndex(cellPane,0);
            GridPane.setColumnIndex(cellPane,i);

            mapGrid.getChildren().add(cellPane);
        }

        Rectangle cell1 = new Rectangle (gridCellWidth,gridCellWidth);
        cell1.setStroke(BLACK);
        cell1.setFill(GREY);
        Text cellText1 = new Text("");

        StackPane cellPane1 = new StackPane();
        cellPane1.getChildren().addAll(cell1,cellText1);

        GridPane.setRowIndex(cellPane1,0);
        GridPane.setColumnIndex(cellPane1,c+1);

        mapGrid.getChildren().add(cellPane1);

        for (int i = b; i <= d; i++) {
            Rectangle cell = new Rectangle (gridCellWidth,gridCellWidth);
            cell.setStroke(BLACK);
            cell.setFill(GREY);
            Text cellText = new Text(i+"");

            StackPane cellPane = new StackPane();
            cellPane.getChildren().addAll(cell,cellText);

            GridPane.setRowIndex(cellPane,d+1-i);
            GridPane.setColumnIndex(cellPane,c+1);

            mapGrid.getChildren().add(cellPane);
        }

    }


    public void updateMapGrid() throws InterruptedException {
        clearGrid();
        WorldMap worldMap = simulation.getWorldMap();
        int a = worldMap.getBoundaries().lowerLeft().x();
        int b = worldMap.getBoundaries().lowerLeft().y();
        int c = worldMap.getBoundaries().upperRight().x();
        int d = worldMap.getBoundaries().upperRight().y();
        double gridCellWidth = GridCellWidthCalculation.determineGridCellWidth (Math.max(c-a+1,d-b+1));

        drawBorder (a,b,c,d);

        for (int j = d; j >= b; j--) {
            for (int i = a; i <= c; i++) {
                Rectangle cell = new Rectangle (gridCellWidth,gridCellWidth);
                cell.setStroke(BLACK);
                cell.setFill(TRANSPARENT);
                Text cellText = new Text("");
                cellText.setText (worldMap.objectAtToString(new Position(i,j)));

                //color depending on the level of energy/ purple if the animal on the particular fields is being tracked
                if (!cellText.getText().equals("") && !cellText.getText().equals("*")) {
                    cellText.setFill(WHITE);

                    if (worldMap.getTopAnimalAt(new Position(i,j)) != null && worldMap.getTopAnimalAt(new Position(i,j)).isBeingTracked()) {
                        cell.setFill(PURPLE);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 5) {
                        cell.setFill(DARKRED);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 10) {
                        cell.setFill(RED);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 15) {
                        cell.setFill(DARKORANGE);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 20) {
                        cell.setFill(ORANGE);
                        cellText.setFill(BLACK);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 30) {
                        cell.setFill(YELLOW);
                        cellText.setFill(BLACK);
                    }
                    else if (worldMap.getTopAnimalAt(new Position(i,j)).getEnergy() <= 45) {
                        cell.setFill(GREEN);
                    }
                    else {
                        cell.setFill(DARKGREEN);
                    }
                }

                if (worldMap.getRecentDeathPlaces().contains(new Position(i,j))) {
                    cell.setStroke(LIGHTBLUE);
                }

                StackPane cellPane = new StackPane();
                cellPane.getChildren().addAll(cell,cellText);

                GridPane.setRowIndex(cellPane,d-j+1);
                GridPane.setColumnIndex(cellPane,i);

                mapGrid.getChildren().add(cellPane);
            }
        }

    }

    public void changeStatistics (int numberOfAnimals, int numberOfPlants, int mostPopularGene, double averageEnergy, double averageLifetime, double averageNumberOfChildren, int day, int numberOfUnoccupiedFields) throws InterruptedException{
        this.dayCounter.setText(day+"");
        this.numberOfAliveAnimals.setText(numberOfAnimals+"");
        this.numberOfPlants.setText(numberOfPlants+"");
        this.numberOfUnoccupiedFields.setText(numberOfUnoccupiedFields+"");
        this.mostPopularGene.setText(mostPopularGene+"");
        this.averageEnergy.setText(BigDecimal.valueOf(averageEnergy).setScale(4) + "");
        this.averageLifetime.setText(BigDecimal.valueOf(averageLifetime).setScale(4)+"");
        this.averageNumberOfChildren.setText(BigDecimal.valueOf(averageNumberOfChildren).setScale(4)+"");

        if (saveFileName != null) {
            String[] savedSimulationData = {
                    "Day: " + day,
                    "Number of animals: " + numberOfAnimals,
                    "Number of plants: " + numberOfPlants,
                    "Most popular gene: " + mostPopularGene,
                    "Average energy: " + averageEnergy,
                    "Average lifetime among the already buried animals: " + averageLifetime,
                    "Average number of children: " + averageNumberOfChildren,
                    "Number of unoccupied fields: " + numberOfUnoccupiedFields,
                    " "
            };
            SimulationFileWriter.saveToFile("saved_simulation_data",saveFileName,savedSimulationData,true);
        }
    }

    public void changeAnimalInformation (Position animalPosition, ArrayList<Integer> genome, int activeGene, int energy, int numberOfEatenPlants, int numberOfChildren, int lifetime, int deathDate, int numberOfDescendants) throws InterruptedException {
        this.animalPosition.setText(animalPosition+"");
        animalGenome.setText(genome+"");
        activeIndex.setText(activeGene+"");
        this.energy.setText(energy+"");
        this.numberOfEatenPlants.setText(numberOfEatenPlants+"");
        this.numberOfChildren.setText(numberOfChildren+"");
        animalDescendants.setText(numberOfDescendants+"");
        animalLifetime.setText(lifetime+"");
        animalDeathDate.setText(deathDate+"");
    }

    public void onStartClicked () {
        if (started == false) {
            started = true;
            simulationThread = new Thread(simulation);
            simulationThread.start();
        }
    }

    public void onStopClicked () {
        simulation.stop();
    }

    public void onResumeClicked () {
        simulation.resume();
    }

    private void trackCatchErrors () throws IllegalArgumentException {
        int x = Parser.parse(animalTrackerXCoord.getText());
        int y = Parser.parse(animalTrackerYCoord.getText());
        Position position = new Position(x,y);
        if (!simulation.getWorldMap().withinBoundariesX(position) || !simulation.getWorldMap().withinBoundariesY(position)) {
            throw new IllegalArgumentException();
        }
    }

    public void onTrackClicked () {
        if (!simulation.isStopped()) {
            simulationErrorMessage.setText("Please stop the simulation first");
        }
        else {
            try {
                trackCatchErrors ();
                simulationErrorMessage.setText("");
                int x = Parser.parse(animalTrackerXCoord.getText());
                int y = Parser.parse(animalTrackerYCoord.getText());
                Position position = new Position(x,y);
                simulation.startTracking(position,this);
            }
            catch (IllegalArgumentException exception) {
                simulationErrorMessage.setText("Wrong input");
            }
        }

    }

    public void onUntrackClicked () {
        simulation.stopTracking();
        animalPosition.setText("N/A");
        animalGenome.setText("N/A");
        activeIndex.setText("N/A");
        energy.setText("N/A");
        numberOfEatenPlants.setText("N/A");
        numberOfChildren.setText("N/A");
        animalDescendants.setText("N/A");
        animalLifetime.setText("N/A");
        animalDeathDate.setText("N/A");

    }

    public void onTerminateClicked () {
        if (everInterrupted == false) {
            everInterrupted = true;
            simulationThread.interrupt();
        }
    }

    public void onShowAnimalsWithTheMostPopularGeneClicked () {
        if (simulation.isStopped() && !mostPopularGene.getText().equals("N/A")) {
            simulationErrorMessage.setText("");
            Set<Position> positionsOfTheAnimalsWithTheMostPopularGene = simulation.getPositionsOfAnimalsWithACertainGene(Parser.parse(mostPopularGene.getText()));

            clearGrid();
            int a = simulation.getWorldMap().getBoundaries().lowerLeft().x();
            int b = simulation.getWorldMap().getBoundaries().lowerLeft().y();
            int c = simulation.getWorldMap().getBoundaries().upperRight().x();
            int d = simulation.getWorldMap().getBoundaries().upperRight().y();
            double gridCellWidth = GridCellWidthCalculation.determineGridCellWidth(Math.max(c - a + 1, d - b + 1));

            drawBorder(a, b, c, d);

            for (int j = d; j >= b; j--) {
                for (int i = a; i <= c; i++) {
                    Rectangle cell = new Rectangle(gridCellWidth, gridCellWidth);
                    cell.setStroke(BLACK);
                    if (positionsOfTheAnimalsWithTheMostPopularGene.contains(new Position(i,j))) {
                        cell.setFill(PINK);
                    }
                    else {
                        cell.setFill(TRANSPARENT);
                    }
                    Text cellText = new Text("");
                    cellText.setText(simulation.getWorldMap().objectAtToString(new Position(i, j)));


                    StackPane cellPane = new StackPane();
                    cellPane.getChildren().addAll(cell, cellText);

                    GridPane.setRowIndex(cellPane, d - j + 1);
                    GridPane.setColumnIndex(cellPane, i);

                    mapGrid.getChildren().add(cellPane);
                }
            }
            simulationErrorMessage.setText("");

        }
        else if (!simulation.isStopped()) {
           simulationErrorMessage.setText("Please stop the simulation first");
        }
        else {
            simulationErrorMessage.setText("What gene is the most popular has not been determined yet");
        }
    }

    public void onShowFieldsPreferredByPlantsClicked () {
        if (simulation.isStopped()) {
            simulationErrorMessage.setText("");
            Set<Position> preferredFields = simulation.getFieldsPreferredByPlants();

            clearGrid();
            int a = simulation.getWorldMap().getBoundaries().lowerLeft().x();
            int b = simulation.getWorldMap().getBoundaries().lowerLeft().y();
            int c = simulation.getWorldMap().getBoundaries().upperRight().x();
            int d = simulation.getWorldMap().getBoundaries().upperRight().y();
            double gridCellWidth = GridCellWidthCalculation.determineGridCellWidth(Math.max(c - a + 1, d - b + 1));

            drawBorder(a, b, c, d);

            for (int j = d; j >= b; j--) {
                for (int i = a; i <= c; i++) {
                    Rectangle cell = new Rectangle(gridCellWidth, gridCellWidth);
                    cell.setStroke(BLACK);
                    if (preferredFields.contains(new Position(i,j))) {
                        cell.setFill(LIGHTBLUE);
                    }
                    else {
                        cell.setFill(TRANSPARENT);
                    }
                    Text cellText = new Text("");
                    cellText.setText(simulation.getWorldMap().objectAtToString(new Position(i, j)));


                    StackPane cellPane = new StackPane();
                    cellPane.getChildren().addAll(cell, cellText);

                    GridPane.setRowIndex(cellPane, d - j + 1);
                    GridPane.setColumnIndex(cellPane, i);

                    mapGrid.getChildren().add(cellPane);
                }
            }
            simulationErrorMessage.setText("");

        }
        else {
            simulationErrorMessage.setText("Please stop the simulation first");
        }
    }

    public void onShowAllPlantsClicked () {
        if (simulation.isStopped()) {
            simulationErrorMessage.setText("");
            clearGrid();
            WorldMap worldMap = simulation.getWorldMap();
            int a = worldMap.getBoundaries().lowerLeft().x();
            int b = worldMap.getBoundaries().lowerLeft().y();
            int c = worldMap.getBoundaries().upperRight().x();
            int d = worldMap.getBoundaries().upperRight().y();
            double gridCellWidth = GridCellWidthCalculation.determineGridCellWidth(Math.max(c - a + 1, d - b + 1));

            drawBorder(a, b, c, d);

            for (int j = d; j >= b; j--) {
                for (int i = a; i <= c; i++) {
                    Rectangle cell = new Rectangle(gridCellWidth, gridCellWidth);
                    cell.setStroke(BLACK);
                    cell.setFill(TRANSPARENT);
                    Text cellText = new Text("");
                    cellText.setText(worldMap.plantAtToString(new Position(i, j)));

                    StackPane cellPane = new StackPane();
                    cellPane.getChildren().addAll(cell, cellText);

                    GridPane.setRowIndex(cellPane, d - j + 1);
                    GridPane.setColumnIndex(cellPane, i);

                    mapGrid.getChildren().add(cellPane);
                }
            }
        }
        else {
            simulationErrorMessage.setText("Please stop the simulation first");
        }
    }

}
