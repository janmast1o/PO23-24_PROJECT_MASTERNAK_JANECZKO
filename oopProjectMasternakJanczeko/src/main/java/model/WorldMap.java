package model;
import animal.AnimalCluster;
import animal.Animal;
import presenter.MapChangeListener;
import java.util.*;

public class WorldMap {

    private Boundaries boundaries;

    private HashMap<Position,Integer> plants;

    private HashMap<Position,AnimalCluster> animalClusters;

    private Set<MapChangeListener> listeners;

    public WorldMap (Boundaries boundaries) {
        this.boundaries = boundaries;
        plants = new HashMap<>();
        animalClusters = new HashMap<>();
        listeners = new HashSet<>();
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

    public void turnAroundAnimal (Position position, Animal animal) {
        changesOccurred(position);
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

    public Position getRandomPositionOnTheMap () {
        return boundaries.getRandomPositionWithinBoundaries();
    }

    public void addListener (MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener (MapChangeListener listener) {
        listeners.remove(listener);
    }

    private void changesOccurred (Position position) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this,position);
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