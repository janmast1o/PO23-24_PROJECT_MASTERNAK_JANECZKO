package simulation;

import animal.Animal;
import model.WorldMap;

public class Gravedigger {

    private WorldMap worldMap;

    protected Gravedigger (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void removeDeadAnimals (int day, SimulationInformation simulationInformation) {
        for (Animal animal : worldMap.getAnimalList()) {
            if (!animal.isAlive()) {
                worldMap.removeAnimal(animal.getPosition(), animal);
                worldMap.registerDeathOfAnAnimal(animal.getPosition(), animal);
                animal.setDeathDate(day);
                simulationInformation.addDeathCertificate(animal,day);
            }
        }

    }

}
