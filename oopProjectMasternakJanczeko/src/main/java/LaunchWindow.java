import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import simulation.Simulation;
import simulation.SimulationInitializer;

public class LaunchWindow {

    @FXML
    Spinner<Integer> mapWidth;

    @FXML
    Spinner<Integer> mapHeight;

    @FXML
    ComboBox<String> mapVariant;

    @FXML
    Spinner<Integer> initialNumberOfPlants;

    @FXML
    Spinner<Integer> plantsNutritionalValue;

    @FXML
    Spinner<Integer> numberOfPlantsGrownPerDay;

    @FXML
    ComboBox<String> plantGrowthVariant;

    @FXML
    Spinner<Integer> initialNumberOfAnimals;

    @FXML
    Spinner<Integer> startingEnergy;

    @FXML
    Spinner<Integer> sufficientReproductionEnergy;

    @FXML
    Spinner<Integer> energyLostAfterReproduction;

    @FXML
    Spinner<Integer> minNumberOfMutations;

    @FXML
    Spinner<Integer> maxNumberOfMutations;

    @FXML
    ComboBox<String> mutationVariant;

    @FXML
    Spinner<Integer> lengthOfTheGenome;

    @FXML
    ComboBox<String> animalBehaviorVariant;

    public int getSpinner(Spinner<Integer> spinner) {
        return spinner.getValue();
    }

    public String getMutationMode() {
        if (mutationVariant.toString().equals("Full randomness")) {
            return "default";
        }
        return "not_default";
    }

    public String getPlantGrowthMode() {
        if (plantGrowthVariant.toString().equals("Forested equator")) {
            return "default";
        }
        return "not_default";
    }

    public void onStartClicked() {
        SimulationInitializer simulationInitializer = new SimulationInitializer(
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
                (getSpinner(mapHeight) / 10),
                getSpinner(numberOfPlantsGrownPerDay)
        );

        try {
            Simulation simulation = simulationInitializer.initializeSimulation(getMutationMode(), getPlantGrowthMode());
            //Thread thread = new Thread(simulation);
            //thread.start();
            //Application.launch(SimulationApp.class);
            simulation.run();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        finally {
            System.out.println("closing simulation");
        }
    }
}