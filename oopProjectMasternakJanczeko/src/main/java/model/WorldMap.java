package model;

import Animal.AnimalCluster;
import Animal.Animal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WorldMap {

    private Boundaries boundaries;

    private HashMap<Position,Integer> plants;

    private HashMap<Position, AnimalCluster> animalsClusters;

    private List<Animal> animals;


    public WorldMap (Boundaries boundaries) {
        this.boundaries = boundaries;
        plants = new HashMap<>();
        animalsClusters = new HashMap<>();
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public boolean withinBoundaries (Position position) {
        return boundaries.withinBoundaries (position);
    }

    public int mapSize () {
        return boundaries.size();
    }

    public int getNumberOfUnoccupiedPositions () {
        return mapSize()- animalsClusters.size();
    }

    public HashMap<Position, Integer> getPlants() {
        return plants;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void growAPlant (Position position, Integer nutritionalValue) {
        plants.put (position,nutritionalValue);
    }

    public void removePlant (Position position) {
        plants.remove (position);
    }

    public void placeAnimal () {
        ;
    }

    public void removeAnimal () {
        ;
    }

    public void moveAnimal () {
        ;
    }

    public boolean isAnimalAt (Position position) {
        return animalsClusters.containsKey(position);
    }

    public boolean isPlantAt (Position position) {
        return plants.containsKey(position);
    }

    public List<Position> getOccupiedByBothAnimalsAndPlants () {
        List<Position> occupiedByBothAnimalsAndPlants = new LinkedList<>();
        for (Position position : animalsClusters.keySet()) {
            if (isPlantAt(position)) {
                occupiedByBothAnimalsAndPlants.add(position);
            }
        }
        return occupiedByBothAnimalsAndPlants;
    }

    public AnimalCluster animalsAt (Position position) {
        return animalsClusters.get(position);
    }

    public Animal getTopAnimalAt (Position position) {
        return animalsClusters.get(position).getTopAnimal();
    }

    public Animal getSecondToTheTopAnimalAt (Position position) {
        return animalsClusters.get(position).getSecondToTheTopAnimal();
    }

    public List<Position> getClustersOfTwoOrMoreAnimals () {
        List<Position> positions = new LinkedList<>();
        for (Position position : animalsClusters.keySet()) {
            if (animalsClusters.get(position).areThereTwoAnimalsInCluster()) {
                positions.add(position);
            }
        }
        return positions;
    }

    public boolean isMapEmpty () {
        return animals.size() == 0;
    }

}
