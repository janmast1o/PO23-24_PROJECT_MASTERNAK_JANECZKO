import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import presenter.SimulationApp;
import simulation.Simulation;
import simulation.SimulationInitializer;
import util.Parser;

public class LaunchWindow {

    @FXML
    TextField mapWidth;

    @FXML
    TextField mapHeight;

    @FXML
    TextField initialNumberOfPlants;

    @FXML
    TextField initialNumberOfAnimals;

    @FXML
    TextField startingEnergy;

    @FXML
    TextField plantsNutritionalValue;

    @FXML
    TextField sufficientReproductionEnergy;

    @FXML
    TextField energyLostAfterReproduction;

    @FXML
    TextField lengthOfTheGenome;

    @FXML
    TextField minNumberOfMutations;

    @FXML
    TextField maxNumberOfMutations;

    @FXML
    TextField equatorSpan;

    @FXML
    TextField numberOfPlantsGrownPerDay;


    private int getInput (TextField field) {
        int parsed = 0;
        try {
            parsed = Parser.parse (field.getText().split(""));
        }
        catch (IllegalArgumentException exception) {
            System.exit(1);
        }
        return parsed;
    }

    public void onStartClicked () {
        SimulationInitializer simulationInitializer = new SimulationInitializer (
                getInput(mapWidth),
                getInput(mapHeight),
                getInput(initialNumberOfPlants),
                getInput(initialNumberOfAnimals),
                getInput(startingEnergy),
                getInput(plantsNutritionalValue),
                getInput(sufficientReproductionEnergy),
                getInput(lengthOfTheGenome),
                getInput(minNumberOfMutations),
                getInput(maxNumberOfMutations),
                getInput(energyLostAfterReproduction),
                getInput(equatorSpan),
                getInput(numberOfPlantsGrownPerDay)
        );

        Simulation simulation = simulationInitializer.initializeSimulation();
        Application.launch(SimulationApp.class);

    }

}
