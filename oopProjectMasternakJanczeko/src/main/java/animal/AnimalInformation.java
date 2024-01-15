package animal;
import java.util.HashSet;

public class AnimalInformation {

    private int lifetime;

    private int numberOfEatenPlants;

    private HashSet<Animal> children;

    protected AnimalInformation () {
        lifetime = 0;
        numberOfEatenPlants = 0;
        children = new HashSet<>();
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

}