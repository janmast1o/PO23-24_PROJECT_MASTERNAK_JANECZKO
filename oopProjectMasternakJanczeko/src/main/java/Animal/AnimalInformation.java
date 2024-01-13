package Animal;

import Animal.Animal;
import model.Date;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class AnimalInformation {

    private final Date birth;

    private Date death;

    private int numberOfEatenPlants;

    private HashSet<Animal> children;

    protected AnimalInformation (int birth) {
        this.birth = new Date (birth);
        numberOfEatenPlants = 0;
        children = new HashSet<>();
    }

    protected Date getBirth () {
        return birth;
    }

    protected Optional<Date> getDeath () {
        return Optional.ofNullable(death);
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

    protected void die (int deathDate) {
        death = new Date (deathDate);
    }

    protected boolean isAlive () {
        return death == null;
    }

}
