package model;
import java.util.HashMap;

public class WorldMap {

    private Boundaries boundaries;

    private HashMap<Position,Integer> plants;

    private HashMap<Position,AnimalCluster> animals;


    public WorldMap (Boundaries boundaries) {
        this.boundaries = boundaries;
        this.plants = new HashMap<>();
        this.animals = new HashMap<>();
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public HashMap<Position, Integer> getPlants() {
        return plants;
    }

    public HashMap<Position, AnimalCluster> getAnimals() {
        return animals;
    }

    public void growAPlant (Position position, Integer nutritionalValue) {
        plants.put (position,nutritionalValue);
    }

    public void removeAPlant (Position position) {
        plants.remove (position);
    }

    public void placeAnimal (Animal animal) {
        if (!animals.containsKey (animal.getPosition())) {
            animals.put (animal.getPosition(), new AnimalCluster());
        }
        animals.get(animal.getPosition()).addAnimal(animal);
    }

    public void removeAnimal (Position position, Animal animal) {
        animals.get(position).removeAnimal(animal);
        if (animals.get(position).size() == 0) {
            animals.remove (animal.getPosition());
        }
    }

//    public AnimalCluster animalClusterAt (Position position) {
//        return
//    }
}