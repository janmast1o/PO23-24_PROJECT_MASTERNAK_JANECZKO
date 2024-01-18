package animal;

import java.util.HashSet;

public class AnimalInformation {

    private int lifetime;

    private int numberOfEatenPlants;

    private HashSet<Animal> children;

    private int deathDate;

    protected AnimalInformation () {
        lifetime = 0;
        numberOfEatenPlants = 0;
        children = new HashSet<>();
        deathDate = -1;
    }

    protected int getLifetime () {
        return lifetime;
    }

    protected int getNumberOfEatenPlants () {
        return numberOfEatenPlants;
    }

    protected HashSet<Animal> getChildren () {
        return children;
    }

    protected void registerPlantConsumption () {
        numberOfEatenPlants++;
    }

    protected void addChild (Animal child) {
        children.add (child);
    }

    protected int getNumberOfChildren () {
        return children.size();
    }

    protected void ageByADay () {
        lifetime++;
    }

    protected int getDeathDate () {
        return deathDate;
    }

    protected void setDeathDate (int deathDate) {
        this.deathDate = deathDate;
    }

}
