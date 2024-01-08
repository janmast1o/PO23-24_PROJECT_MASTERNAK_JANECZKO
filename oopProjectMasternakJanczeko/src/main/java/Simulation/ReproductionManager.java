package Simulation;

import model.Animal;
import model.Position;
import util.GenesGiven;

import java.util.ArrayList;

public class ReproductionManager {

    private Simulation simulation;

    public ReproductionManager (Simulation simulation) {
        simulation = this.simulation;
    }

    public void reproduce (Position position) {
        if (simulation.getMap().getAnimals().get(position).size() > 1) {
            internalReproduce (simulation.getMap().getAnimals().get(position).getTopAnimal(),
                    simulation.getMap().getAnimals().get(position).getSecondToTheTopAnimal());
        }
    }

    private void internalReproduce (Animal topAnimal, Animal secondToTheTopAnimal) {
        ArrayList<Integer> childGenes = GenesGiven.createChildGenes (topAnimal, secondToTheTopAnimal);
        Animal child = new Animal (simulation.getDay(), topAnimal.getPosition(), childGenes, simulation.getInformation().startingEnergy());
        simulation.getMap().placeAnimal (child);
    }

}
