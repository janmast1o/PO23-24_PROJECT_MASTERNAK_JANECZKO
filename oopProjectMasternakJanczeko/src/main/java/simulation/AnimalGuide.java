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
            if (!worldMap.withinBoundariesY(animal.previewMovement())) {
                animal.turnAround();
            }
            else if (!worldMap.withinBoundariesX(animal.previewMovement())) {
                Position previousPosition = animal.getPosition();
                animal.move();
                animal.teleport(worldMap.transcendVerticalBorder(animal.getPosition()));
                worldMap.moveAnimal(previousPosition, animal.getPosition(), animal);
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
