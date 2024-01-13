package Simulation;

import Animal.Animal;
import model.Position;
import model.WorldMap;

import java.util.Iterator;

public class AnimalGuide {

    private WorldMap worldMap;

    protected AnimalGuide (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void moveAnimals () {
        for (Animal animal : worldMap.getAnimals()) {
            if (!worldMap.withinBoundaries(animal.previewMovement())) {
                animal.turnAround();
            }
            else {
                Position previousPosition = animal.getPosition();
                animal.move();
                worldMap.moveAnimal(previousPosition,animal);
            }
        }

    }

}
