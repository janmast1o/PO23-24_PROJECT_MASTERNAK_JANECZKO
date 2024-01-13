package Simulation;

import Animal.Animal;
import model.WorldMap;

import java.util.Iterator;

public class Gravedigger {

    private WorldMap worldMap;

    protected Gravedigger (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void removeDeadAnimals () {
        Iterator<Animal> animalIterator = worldMap.getAnimals().iterator();
        Animal animal;

        while (animalIterator.hasNext()) {
            animal = animalIterator.next();
            if (!animal.isAlive()) {
                worldMap.removeAnimal(animal); //!!
            }
        }

    }

}
