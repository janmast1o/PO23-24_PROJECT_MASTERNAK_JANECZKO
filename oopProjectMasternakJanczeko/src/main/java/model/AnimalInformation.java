package model;
import java.util.HashSet;

public class AnimalInformation {
    private HashSet<Animal> children;
    private int numberOfEatenPlants;
    private final int birth;
    private int death = -1; //as long as -1 that means animal is alive

    public AnimalInformation (int birth) {
        numberOfEatenPlants = 0;
        children = new HashSet<>();
        this.birth = birth;
    }

    public HashSet<Animal> getChildren () {
        return children;
    }

    public int getBirth () {
        return birth;
    }


    public void addChild(Animal child) {
        children.add (child);
    }

    public void die (int death) {
        this.death = death;
    }

    public int getNumberOfDescendants() {
        return children.size();
    }

    public void registerPlantConsumption () {
        numberOfEatenPlants++;
    }

    public int getDeath () {
        return death;
    }
}