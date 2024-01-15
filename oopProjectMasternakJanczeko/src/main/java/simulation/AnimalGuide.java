package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;

public class AnimalGuide {

    private WorldMap worldMap;

    protected AnimalGuide (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void moveAnimals () {
        for (Animal animal : worldMap.getAnimalList()) {
            if (!worldMap.withinBoundaries(animal.previewMovement())) {
                animal.turnAround();
            }
            else {
                Position previousPosition = animal.getPosition();
                animal.move();
                worldMap.moveAnimal(previousPosition, animal.getPosition(), animal);
            }
            animal.incrementActiveGene();
        }

    }

}
