package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;

public class DefaultAnimalGuide implements AnimalGuide {

    private WorldMap worldMap;

    private boolean stopped;

    public DefaultAnimalGuide(WorldMap worldMap) {
        stopped = false;
        this.worldMap = worldMap;
    }

    @Override
    public void stop() {
        stopped = true;
    }

    @Override
    public void resume() {
        stopped = false;
        synchronized (this) {
            this.notify();
        }
    }


    @Override
    public void moveAnimals () {
        for (Animal animal : worldMap.getAnimalList()) {
            if (!worldMap.withinBoundariesY(animal.previewMovement())) {
                animal.turnAround();
                worldMap.animalTurnedAround(animal.getPosition());
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

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this) {
                while (stopped) {
                    try {
                        this.wait();
                    } catch (
                            InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }try {
                Thread.sleep(650);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
