package Simulation;

import model.Animal;
import model.AnimalCluster;
import model.Position;

public class PlantManager {

    private Simulation simulation;

    public PlantManager (Simulation simulation) {
        this.simulation = simulation;
    }

    public void eatPlant (AnimalCluster animalCluster, Position position) {
        internalEatPlant (animalCluster.getTopAnimal(), simulation.getMap().getPlants().get(position));
        simulation.getMap().removeAPlant (position);
    }

    private void internalEatPlant (Animal animal, Integer nutritionalValue) {
        if (! (animal == null)) {
            animal.changeEnergy (nutritionalValue);
        }
    }

}
