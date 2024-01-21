import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presenter.SimulationPresenter;
import simulation.Simulation;
import util.Parser;
import util.SimulationFileReader;
import util.SimulationFileWriter;
import util.SimulationInitializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private TextField saveConfigField;

    @FXML
    private TextField loadConfigField;

    @FXML
    private CheckBox shouldDataBeSaved;

    @FXML
    private TextField saveDataFileName;

    private int getSpinner(Spinner<Integer> spinner) {
        return (int) spinner.getValue();
    }

    private void setSpinner (Spinner<Integer> spinner, int spinnerValue) {
        spinner.getValueFactory().setValue(spinnerValue);
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

    private void openNewSimulationWindow (Simulation newSimulation, String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 300);
            SimulationPresenter simulationPresenter = loader.getController();
            simulationPresenter.setSimulation(newSimulation);
            simulationPresenter.assignANewListenerAndObserverToSimulation();
            if (fileName != null) {
                simulationPresenter.setSaveFileName(fileName);
            }

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
        else if (shouldDataBeSaved.isSelected() && (saveDataFileName.getText().equals("") || saveDataFileName.getText().contains("/"))) {
            throw new IllegalArgumentException("The name of the file should not be of length 0 and should not contain '/'");
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

            if (!shouldDataBeSaved.isSelected()) {
                openNewSimulationWindow(newSimulation,null);
            }
            else {
                String[] simulationDetails = {
                        "Simulation started on: " + LocalDateTime.now(),
                        "Map width: " + getSpinner(mapWidth),
                        "Map height: " + getSpinner(mapHeight),
                        "Initial number of plants: " + getSpinner(initialNumberOfPlants),
                        "Initial number of animals: " + getSpinner(initialNumberOfAnimals),
                        "Starting energy of those animals: " + getSpinner(startingEnergy),
                        "Plant's nutritional value: " + getSpinner(plantsNutritionalValue),
                        "Sufficient reproduction energy" + getSpinner(sufficientReproductionEnergy),
                        "Energy lost after reproduction: " + getSpinner(energyLostAfterReproduction),
                        "Length of the genome: " + getSpinner(lengthOfTheGenome),
                        "Min number of mutations: " + getSpinner(minNumberOfMutations),
                        "Max number of mutations: " + getSpinner(maxNumberOfMutations),
                        "Number of plants grown each day" + getSpinner(numberOfPlantsGrownPerDay),
                        "Mutation mode: " + getMutationMode(),
                        "PlantGrowthMode: " + getPlantGrowthMode(),
                        " "
                };
                SimulationFileWriter.saveToFile("saved_simulation_data",saveDataFileName.getText(),simulationDetails,true);
                openNewSimulationWindow(newSimulation,saveDataFileName.getText());
            }
            wrongInputErrorMessage.setText("");
        }
        catch (IllegalArgumentException exception) {
            wrongInputErrorMessage.setText(exception.getMessage());
        }

    }

    public void onSaveConfigClicked() {
        String fileName = saveConfigField.getText();
        if (!fileName.equals("")) {
            String[] savedConfig = {
                    String.valueOf(getSpinner(mapWidth)),
                    String.valueOf(getSpinner(mapHeight)),
                    String.valueOf(getSpinner(initialNumberOfPlants)),
                    String.valueOf(getSpinner(initialNumberOfAnimals)),
                    String.valueOf(getSpinner(startingEnergy)),
                    String.valueOf(getSpinner(plantsNutritionalValue)),
                    String.valueOf(getSpinner(sufficientReproductionEnergy)),
                    String.valueOf(getSpinner(energyLostAfterReproduction)),
                    String.valueOf(getSpinner(lengthOfTheGenome)),
                    String.valueOf(getSpinner(minNumberOfMutations)),
                    String.valueOf(getSpinner(maxNumberOfMutations)),
                    String.valueOf(getSpinner(numberOfPlantsGrownPerDay))
            };
            SimulationFileWriter.saveToFile("saved_configurations", fileName, savedConfig, false);
        }
        else {
            wrongInputErrorMessage.setText("Please type in a valid name");
        }
    }

    public void onLoadConfigClicked() {
        String fileName = loadConfigField.getText();
        ArrayList<String> savedConfig = SimulationFileReader.readFile("saved_configurations",fileName);
        if (savedConfig != null) {
            List<Spinner<Integer>> spinners = List.of (
                    mapWidth, mapHeight, initialNumberOfPlants, initialNumberOfAnimals, startingEnergy, plantsNutritionalValue,
                    sufficientReproductionEnergy, energyLostAfterReproduction, lengthOfTheGenome,
                    minNumberOfMutations, maxNumberOfMutations, numberOfPlantsGrownPerDay
            );
            int i = 0;
            for (Spinner spinner : spinners) {
                setSpinner(spinner, Parser.parse(savedConfig.get(i)));
                i++;
            }
        }
    }

}
