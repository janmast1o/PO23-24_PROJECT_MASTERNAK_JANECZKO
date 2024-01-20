package presenter;

import javafx.application.Platform;
import model.Position;

import java.util.ArrayList;

public class UIAnimalTracker {

    private SimulationPresenter simulationPresenter;

    public UIAnimalTracker (SimulationPresenter simulationPresenter) {
        this.simulationPresenter = simulationPresenter;
    }

    public void animalChanged (Position animalPosition, ArrayList<Integer> genome, int activeGene, int energy, int numberOfEatenPlants, int numberOfChildren, int lifetime, int deathDate, int numberOfDescendants) {
        Platform.runLater(()-> {
            try {
                simulationPresenter.changeAnimalInformation(
                        animalPosition,genome,activeGene,energy,numberOfEatenPlants,numberOfChildren,lifetime,deathDate,numberOfDescendants
                );
            }
            catch (InterruptedException exception) {
                System.exit(1);
            }
        });
    }
}
