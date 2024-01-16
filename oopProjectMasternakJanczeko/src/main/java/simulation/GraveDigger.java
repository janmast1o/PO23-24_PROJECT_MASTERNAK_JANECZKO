package simulation;
import animal.Animal;
import model.WorldMap;

public class GraveDigger {

    private WorldMap worldMap;

    protected GraveDigger(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void removeDeadAnimals (int day) {
        System.out.println("removeDeadAnimals elo");
        for (Animal animal : worldMap.getAnimalList()) {
            System.out.println("removeDeadAnimals started");
            if (!animal.isAlive()) {
                worldMap.removeAnimal(animal.getPosition(), animal);
            }
        }

    }
}