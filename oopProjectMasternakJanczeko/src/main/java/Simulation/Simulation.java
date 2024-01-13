package Simulation;

import Animal.Animal;
import model.WorldMap;

import java.util.LinkedList;

public class Simulation implements Runnable {

    private int day;

    private WorldMap worldMap;

    private LinkedList<Animal> deadAnimals;

    private Gravedigger gravedigger;

    private AnimalGuide animalGuide;

    private PlantConsumptionManager plantConsumptionManager;

    public Simulation (WorldMap worldMap) {
        day = 1;
        deadAnimals = new LinkedList<>();
        this.worldMap = worldMap;
        gravedigger = new Gravedigger(worldMap);
        animalGuide = new AnimalGuide(worldMap);
        plantConsumptionManager = new PlantConsumptionManager(worldMap);
    }

    public void run () {
        while (!worldMap.isMapEmpty()) {
            gravedigger.removeDeadAnimals();
            animalGuide.moveAnimals();
            plantConsumptionManager.consumePlants(); //!
            day++;
        }

    }

}
