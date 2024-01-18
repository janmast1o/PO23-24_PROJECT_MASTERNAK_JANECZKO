import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import presenter.SimulationPresenter;
import simulation.Simulation;
import util.SimulationInitializer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LaunchWindow {

    @FXML
    private Spinner<Integer> mapWidth;

    @FXML
    private Spinner<Integer> mapHeight;

    @FXML
    private ComboBox<String> mapVariant;

    @FXML
    private Spinner<Integer> initialNumberOfPlants;

    @FXML
    private Spinner<Integer> plantsNutritionalValue;

    @FXML
    private Spinner<Integer> numberOfPlantsGrownPerDay;

    @FXML
    private ComboBox<String> plantGrowthVariant;

    @FXML
    private Spinner<Integer> initialNumberOfAnimals;

    @FXML
    private Spinner<Integer> startingEnergy;

    @FXML
    private Spinner<Integer> sufficientReproductionEnergy;

    @FXML
    private Spinner<Integer> energyLostAfterReproduction;

    @FXML
    private Spinner<Integer> minNumberOfMutations;

    @FXML
    private Spinner<Integer> maxNumberOfMutations;

    @FXML
    private ComboBox<String> mutationVariant;

    @FXML
    private Spinner<Integer> lengthOfTheGenome;

    @FXML
    private ComboBox<String> animalBehaviorVariant;

    @FXML
    private Label wrongInputErrorMessage;

    private int getSpinner(Spinner<Integer> spinner) {
        return (int) spinner.getValue();
    }

    private String getMutationMode() {
        if (mutationVariant.toString().equals("Full randomness")) {
            return "default";
        }
        return "alternative";
    }

    private String getPlantGrowthMode() {
        if (plantGrowthVariant.toString().equals("Forested equator")) {
            return "default";
        }
        return "alternative";
    }

    private void openNewSimulationWindow (Simulation newSimulation) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 300);
            SimulationPresenter simulationPresenter = loader.getController();
            simulationPresenter.setSimulation(newSimulation);
            simulationPresenter.assignANewListenerAndObserverToSimulation();

            stage.setTitle("Simulation Window");
            stage.setScene(scene);
            stage.minWidthProperty().bind(root.minWidthProperty());
            stage.minHeightProperty().bind(root.minHeightProperty());

            stage.show();

        }
        catch(Exception e){
            System.out.println("Can't load new window: " + e.getMessage());
        }

    }

    private void catchWrongInputs() throws IllegalArgumentException {
        if (getSpinner(numberOfPlantsGrownPerDay) > getSpinner(mapWidth)*getSpinner(mapHeight)) {
            throw new IllegalArgumentException("the number of plants grown per day cannot be greater than the size of the map");
        }
        else if (getSpinner(initialNumberOfPlants) > getSpinner(mapWidth)*getSpinner(mapHeight)) {
            throw new IllegalArgumentException("the initial number of plants cannot be greater than the size of the map");
        }
        else if (getSpinner(energyLostAfterReproduction) > getSpinner(sufficientReproductionEnergy)) {
            throw new IllegalArgumentException("energy lost after reproduction cannot be greater than the amount of energy sufficient to reproduce");
        }
        else if (getSpinner(minNumberOfMutations) > getSpinner(maxNumberOfMutations)) {
            throw new IllegalArgumentException("the min number of mutations cannot be greater than the max number of mutations");
        }
        else if (getSpinner(maxNumberOfMutations) > getSpinner(lengthOfTheGenome)) {
            throw new IllegalArgumentException("the max number of mutations cannot be greater than the size of the animals' genomes");
        }
    }

    public void onStartClicked() {
        try {
            catchWrongInputs();
            Simulation newSimulation = SimulationInitializer.initialize(
                    getSpinner(mapWidth),
                    getSpinner(mapHeight),
                    getSpinner(initialNumberOfPlants),
                    getSpinner(initialNumberOfAnimals),
                    getSpinner(startingEnergy),
                    getSpinner(plantsNutritionalValue),
                    getSpinner(sufficientReproductionEnergy),
                    getSpinner(lengthOfTheGenome),
                    getSpinner(minNumberOfMutations),
                    getSpinner(maxNumberOfMutations),
                    getSpinner(energyLostAfterReproduction),
                    getSpinner(numberOfPlantsGrownPerDay),
                    getMutationMode(),
                    getPlantGrowthMode()
            );

            openNewSimulationWindow(newSimulation);
            wrongInputErrorMessage.setText("");
        }
        catch (IllegalArgumentException exception) {
            wrongInputErrorMessage.setText(exception.getMessage());
        }

    }
}
