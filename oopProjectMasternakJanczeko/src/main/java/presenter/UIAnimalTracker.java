package presenter;

import javafx.application.Platform;

import java.util.ArrayList;

public class UIAnimalTracker {

    private SimulationPresenter simulationPresenter;

    public UIAnimalTracker (SimulationPresenter simulationPresenter) {
        this.simulationPresenter = simulationPresenter;
    }

    public void animalChanged (ArrayList<Integer> genome, int activeGene, int energy, int numberOfEatenPlants, int numberOfChildren, int lifetime, int deathDate) {
        Platform.runLater(()-> {
            try {
                simulationPresenter.changeAnimalInformation(
                        genome,activeGene,energy,numberOfEatenPlants,numberOfChildren,lifetime,deathDate
                );
            }
            catch (InterruptedException exception) {
                System.exit(1);
            }
        });
    }
}
