package Animal;

import model.Date;
import model.Position;
import model.WorldDirection;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Animal {

    private Position position;

    private WorldDirection direction;

    private ArrayList<Integer> genom;

    private int activeGene;

    private int energy;

    private AnimalInformation animalInformation;

    public Animal (int birth, Position postition, ArrayList<Integer> genom, int energy) {
        this.position = postition;
        this.genom = genom;
        this.direction = WorldDirection.randomDirection();
        this.energy = energy;
        this.animalInformation = new AnimalInformation (birth);

        Random random = new Random ();
        this.activeGene = random.nextInt(0,genom.size());
    }

    public Position getPosition () {
        return position;
    }

    public int getEnergy () {
        return energy;
    }

    public Position previewMovement () {
        WorldDirection newDirection = direction.performNextNTimes(genom.get(activeGene));
        return position.addPostition (newDirection.toVector());
    }

    public void move () {
        direction = direction.performNextNTimes(genom.get(activeGene));
        position = position.addPostition (direction.toVector());
        activeGene = (activeGene+1)%genom.size();
        energy--;
    }

    public void turnAround () {
        this.direction = direction.performNextNTimes(4);
        activeGene = (activeGene+1)%genom.size();
        energy--;
    }

    public void addChild (Animal child) {
        animalInformation.addChild (child);
    }

    public boolean isAlive () {
        return animalInformation.isAlive();
    }

    public void registerPlantConsumption () {
        animalInformation.registerPlantConsumption();
    }

    public int getNumberOfChildren () {
        return animalInformation.getNumberOfChildren();
    }

    public int getNumberOfEatenPlants () {
        return animalInformation.getNumberOfEatenPlants();
    }

    public Date getBirth () {
        return animalInformation.getBirth();
    }

    public Optional<Date> getDeath () {
        return animalInformation.getDeath();
    }

    public void die (int dateDeath) {
        animalInformation.die (dateDeath);
    }

    public void replenishEnergy (int energy) {
        this.energy += energy;
    }

    public void drainEnergy (int energy) {
        this.energy -= energy;
    }

    public boolean isOutOfEnergy () {
        return energy <= 0;
    }

    public boolean hasSufficientEnergy (int sufficientEnergy) {
        return energy >= sufficientEnergy;
    }

}
