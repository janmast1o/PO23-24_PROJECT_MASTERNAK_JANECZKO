package animal;

import model.Position;
import model.WorldDirection;
import presenter.UIAnimalTracker;
import util.NumberOfDescendantsFinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Animal {

    private Position position;

    private WorldDirection direction;

    private ArrayList<Integer> genome;

    private int activeGene;

    private int energy;

    private AnimalInformation animalInformation;

    private UIAnimalTracker animalTracker;

    public Animal (Position postition, ArrayList<Integer> genome, int energy) {
        this.position = postition;
        this.genome = genome;
        this.direction = WorldDirection.randomDirection();
        this.energy = energy;
        this.animalInformation = new AnimalInformation ();

        Random random = new Random ();
        this.activeGene = random.nextInt(0,genome.size());
    }

    public Position getPosition () {
        return position;
    }

    public int getEnergy () {
        return energy;
    }

    public Integer getGeneAtIndex (int i) {
        return genome.get(i);
    }

    public int getGenomeSize () {
        return genome.size();
    }

    public int getLifeTime () {
        return animalInformation.getLifetime();
    }

    public WorldDirection getDirection () {
        return direction;
    }

    public HashSet<Animal> getChildren () {
        return animalInformation.getChildren();
    }

    public ArrayList<Integer> getGenome () {
        return genome;
    }

    public void incrementActiveGene () {
        activeGene = (activeGene+1)% genome.size();
        changesOccured();
    }

    public Position previewMovement () {
        WorldDirection newDirection = direction.performNextNTimes(genome.get(activeGene));
        return position.addPostition (newDirection.toVector());
    }

    public void move () {
        direction = direction.performNextNTimes(genome.get(activeGene));
        position = position.addPostition (direction.toVector());
        energy--;
        changesOccured();
    }

    public void turnAround () {
        direction = direction.performNextNTimes(genome.get(activeGene));
        direction = direction.performNextNTimes(4);
        energy--;
        changesOccured();
    }

    public void teleport (Position newPosition) {
        position = newPosition;
        energy--;
        changesOccured();
    }

    public void addChild (Animal child) {
        animalInformation.addChild (child);
        changesOccured();
    }

    public boolean isAlive () {
        return energy > 0;
    }

    public void registerPlantConsumption () {
        animalInformation.registerPlantConsumption();
        changesOccured();
    }

    public int getNumberOfChildren () {
        return animalInformation.getNumberOfChildren();
    }

    public int getNumberOfEatenPlants () {
        return animalInformation.getNumberOfEatenPlants();
    }

    public void replenishEnergy (int energy) {
        this.energy += energy;
        changesOccured();
    }

    public void drainEnergy (int energy) {
        this.energy -= energy;
        changesOccured();
    }

    public boolean hasSufficientEnergy (int sufficientEnergy) {
        return energy >= sufficientEnergy;
    }

    public void ageByADay () {
        animalInformation.ageByADay();
        changesOccured();
    }

    @Override
    public String toString () {
        return direction.toString();
    }

    public void setDeathDate (int deathDate) {
        animalInformation.setDeathDate(deathDate);
        changesOccured();
    }

    public int getDeathDate () {
        return animalInformation.getDeathDate();
    }

    public void setTracker (UIAnimalTracker animalTracker) {
        this.animalTracker = animalTracker;
    }

    public void removeTracker () {
        animalTracker = null;
    }

    private void changesOccured () {
        if (animalTracker != null) {
            animalTracker.animalChanged(genome,activeGene,energy,getNumberOfEatenPlants(),getNumberOfChildren(),getLifeTime(),getDeathDate(), NumberOfDescendantsFinder.findNumberOfDescendants(this));
        }
    }

}