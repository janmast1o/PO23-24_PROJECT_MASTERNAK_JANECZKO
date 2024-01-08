package Simulation;

import model.Animal;
import model.Position;

public class AnimalGuide {

    private Simulation simulation;

    public AnimalGuide (Simulation simulation) {
        this.simulation = simulation;
    }

    public void moveAnimal (Animal animal) {
        if (simulation.getMap().getBoundaries().withinBoundaries(animal.newPotentialPosition()) ) {
            Position previousPosition = animal.getPosition();
            animal.moveAccordingToGene ();
            simulation.getMap().removeAnimal (previousPosition, animal);
            simulation.getMap().placeAnimal (animal);
        }
        else {
            animal.turnAround();
        }
    }
}
