package simulation;
import animal.Animal;
import model.WorldMap;

public class GraveDigger {

    private WorldMap worldMap;

    protected GraveDigger(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void removeDeadAnimals (int day) {
        for (Animal animal : worldMap.getAnimalList()) {
            if (!animal.isAlive()) {
                worldMap.removeAnimal(animal.getPosition(), animal);
            }
        }

    }
}