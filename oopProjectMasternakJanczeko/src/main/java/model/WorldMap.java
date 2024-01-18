package model;

import animal.AnimalCluster;
import animal.Animal;
import presenter.MapChangeListener;

import java.util.*;

public class WorldMap {

    private Boundaries boundaries;

    private HashMap<Position,Integer> plants;

    private HashMap<Position,AnimalCluster> animalClusters;

    private MapChangeListener listener;

    private List<Animal> deadAnimals;

    private Set<Position> recentDeathPlaces;

    public WorldMap (int width, int height) {
        this.boundaries = new Boundaries (new Position (0,0), new Position(width-1,height-1));
        plants = new HashMap<>();
        animalClusters = new HashMap<>();
        deadAnimals = new LinkedList<>();
        recentDeathPlaces = new HashSet<>();
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public boolean withinBoundariesX (Position position) {
        return boundaries.withinBoundariesX (position);
    }

    public boolean withinBoundariesY (Position position) {
        return boundaries.withinBoundariesY (position);
    }

    public Position transcendVerticalBorder (Position position) {
        return boundaries.transcendVerticalBorder(position);
    }

    public int mapSize () {
        return boundaries.size();
    }

    public boolean withinEquatorSpan (Position position) {
        return boundaries.withinEquatorSpan(position);
    }

    public int getNumberOfUnoccupiedPositions () {
        return mapSize()- animalClusters.size();
    }

    public int getNumberOfPlants() {
        return plants.size();
    }

    public List<Animal> getAnimalList() {
        List<Animal> animals = new LinkedList<>();
        for (AnimalCluster animalCluster : animalClusters.values()) {
            animals.addAll (animalCluster.getClusterAsList());
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
        changesOccurred(position);
    }

    public void removePlant (Position position) {
        plants.remove (position);
        changesOccurred(position);
    }

    public void placeAnimal (Position position, Animal animal) {
        if (!isOccupiedByAnimals(position)) {
            animalClusters.put(position,new AnimalCluster());
        }
        animalClusters.get(position).addAnimal(animal);
        changesOccurred(position);
    }

    public void removeAnimal (Position position, Animal animal) {
        animalClusters.get(position).removeAnimal(animal);
        if (!isOccupiedByAnimals(position)) {
            animalClusters.remove (position);
        }
        changesOccurred(position);
    }

    public void moveAnimal (Position oldPosition, Position newPosition, Animal animal) {
        removeAnimal(oldPosition,animal);
        placeAnimal(newPosition,animal);
    }

    public void animalTurnedAround (Position position) {
        changesOccurred(position);
    }

    public void registerDeathOfAnAnimal (Position position, Animal animal) {
        deadAnimals.add(animal);
        recentDeathPlaces.add(position);
    }

    public List<Animal> getDeadAnimals () {
        return deadAnimals;
    }

    public Set<Position> getRecentDeathPlaces () {
        return recentDeathPlaces;
    }

    public void clearRecentDeathPlaces () {
        recentDeathPlaces.clear();
    }

    public boolean isOccupiedByPlants(Position position) {
        return plants.containsKey(position);
    }

    public List<Position> getOccupiedByBothAnimalsAndPlants () {
        List<Position> occupiedByBothAnimalsAndPlants = new LinkedList<>();
        for (Position position : animalClusters.keySet()) {
            if (isOccupiedByPlants(position)) {
                occupiedByBothAnimalsAndPlants.add(position);
            }
        }
        return occupiedByBothAnimalsAndPlants;
    }

    public boolean isOccupiedByAnimals(Position position) {
        return animalClusters.containsKey(position) && !animalClusters.get(position).isEmpty();
    }

    public Animal getTopAnimalAt (Position position) throws NullPointerException {
        return animalClusters.get(position).getTopAnimal();
    }

    public Animal getSecondToTheTopAnimalAt (Position position) {
        return animalClusters.get(position).getSecondToTheTopAnimal();
    }

    public Integer getPlantsNutritionalValueAt (Position position) {
        return plants.get(position);
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

    public boolean isEmpty() {
        return animalClusters.size() == 0;
    }

    public Position getRandomPositionOnTheMap () {
        return boundaries.getRandomPositionWithinBoundaries();
    }

    public void addListener (MapChangeListener listener) {
        this.listener = listener;
    }

    public void removeListener (MapChangeListener listener) {
        this.listener = null;
    }

    private void changesOccurred (Position position) {
        if (listener != null) {
            listener.mapChanged(this, position);
        }
    }

    public String objectAtToString (Position position) {
        if (isOccupiedByAnimals(position)) {
            return getTopAnimalAt(position).toString();
        }
        else if (isOccupiedByPlants(position)) {
            return "*";
        }
        else {
            return "";
        }
    }

}
