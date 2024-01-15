package model;

import animal.AnimalCluster;
import animal.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WorldMap {

    private Boundaries boundaries;

    private HashMap<Position,Integer> plants;

    private HashMap<Position,AnimalCluster> animalClusters;

    public WorldMap (Boundaries boundaries) {
        this.boundaries = boundaries;
        plants = new HashMap<>();
        animalClusters = new HashMap<>();
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
        return mapSize()- animalClusters.size();
    }

    public HashMap<Position, Integer> getPlants() {
        return plants;
    }

    public List<Animal> getAnimalList() {
        List<Animal> animals = new LinkedList<>();
        for (AnimalCluster animalCluster : animalClusters.values()) {
            animals.addAll ((List) animalCluster);
        }
        return animals;
    }

    public ArrayList<Position> getFieldsWithNoPlants () {
        ArrayList<Position> positionsWithNoPlants = new ArrayList<>();
        for (Position position : boundaries.getAllFieldsWithinBoundaries()) {
            if (!plants.containsKey(position)) {
                positionsWithNoPlants.add(position);
            }
        }
        return positionsWithNoPlants;
    }

    public void growAPlant (Position position, Integer nutritionalValue) {
        plants.put (position,nutritionalValue);
    }

    public void removePlant (Position position) {
        plants.remove (position);
    }

    public void placeAnimal (Position position, Animal animal) {
        if (!isAnimalAt(position)) {
            animalClusters.put(position,new AnimalCluster());
        }
        animalClusters.get(position).addAnimal(animal);
    }

    public void removeAnimal (Position position, Animal animal) {
        animalClusters.get(position).removeAnimal(animal);
        if (!isAnimalAt(position)) {
            animalClusters.remove (position);
        }
    }

    public void moveAnimal (Position oldPosition, Position newPosition, Animal animal) {
        removeAnimal(oldPosition,animal);
        placeAnimal(newPosition,animal);
    }

    public boolean isPlantAt (Position position) {
        return plants.containsKey(position);
    }

    public List<Position> getOccupiedByBothAnimalsAndPlants () {
        List<Position> occupiedByBothAnimalsAndPlants = new LinkedList<>();
        for (Position position : animalClusters.keySet()) {
            if (isPlantAt(position)) {
                occupiedByBothAnimalsAndPlants.add(position);
            }
        }
        return occupiedByBothAnimalsAndPlants;
    }

    public boolean isAnimalAt (Position position) {
        return animalClusters.containsKey(position) && !animalClusters.get(position).isEmpty();
    }

    public Animal getTopAnimalAt (Position position) {
        return animalClusters.get(position).getTopAnimal();
    }

    public Animal getSecondToTheTopAnimalAt (Position position) {
        return animalClusters.get(position).getSecondToTheTopAnimal();
    }

    public List<Position> getPositionsOfBigClusters () {
        List<Position> positions = new LinkedList<>();
        for (Position position : animalClusters.keySet()) {
            if (animalClusters.get(position).areThereTwoAnimalsInCluster()) {
                positions.add(position);
            }
        }
        return positions;
    }

    public boolean isMapEmpty () {
        return animalClusters.size() == 0;
    }

}
