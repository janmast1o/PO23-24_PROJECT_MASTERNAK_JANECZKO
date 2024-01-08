package Simulation;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable {

    private WorldMap map;

    private LinkedList<Animal> animals;

    private int day;

    private SimulationInformation information;

    private SimulationDetails simulationDetails;

    private AnimalGuide animalGuide;

    private PlantManager plantManager;

    private ReproductionManager reproductionManager;

    public Simulation (WorldMap map, LinkedList<Animal> startAnimals, SimulationInformation information) {
        this.map = map;
        this.day = 0;
        this.animals = startAnimals;
        this.information = information;
        simulationDetails = new SimulationDetails (this);
        animalGuide = new AnimalGuide (this);
        plantManager = new PlantManager (this);
        reproductionManager = new ReproductionManager (this);
    }

    public WorldMap getMap() {
        return map;
    }

    public int getDay () {
        return day;
    }

    public LinkedList<Animal> getAnimals() {
        return animals;
    }

    public SimulationInformation getInformation() {
        return information;
    }

    public SimulationDetails getSimulationDetails () {
        return simulationDetails;
    }

    public void addAnimalToList (Animal animal) {
        animals.add (animal);
    }

    public void run () {
        day++;
        for (Animal animal : animals) {
            if (animal.getDateOfDeath() == -1) {
                animalGuide.moveAnimal (animal);
            }
        }
        for (Position position : map.getAnimals().keySet()) {
            if (map.getPlants().containsKey(position)) {

            }
        }
    }

}