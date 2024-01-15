package animal;
import model.Position;
import model.WorldDirection;
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

    public void incrementActiveGene () {
        activeGene = (activeGene+1)% genome.size();
    }

    public Position previewMovement () {
        WorldDirection newDirection = direction.performNextNTimes(genome.get(activeGene));
        return position.addPosition(newDirection.toVector());
    }

    public void move () {
        direction = direction.performNextNTimes(genome.get(activeGene));
        position = position.addPosition(direction.toVector());
        energy--;
    }

    public void turnAround () {
        this.direction = direction.performNextNTimes(4);
        energy--;
    }

    public void teleport (Position newPosition) {
        position = newPosition;
        energy--;
    }

    public void addChild (Animal child) {
        animalInformation.addChild (child);
    }

    public boolean isAlive () {
        return energy > 0;
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

    public void replenishEnergy (int energy) {
        this.energy += energy;
    }

    public void drainEnergy (int energy) {
        this.energy -= energy;
    }

    public boolean hasSufficientEnergy (int sufficientEnergy) {
        return energy >= sufficientEnergy;
    }

    public void ageByADay () {
        animalInformation.ageByADay();
    }

    @Override
    public String toString () {
        return direction.toString();
    }

}