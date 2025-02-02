package simulation;

import animal.Animal;
import model.WorldMap;

import java.util.List;

public class SimulationInformation {

    private WorldMap worldMap;

    public SimulationInformation (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public int getNumberOfAnimals () {
        return worldMap.getAnimalList().size();
    }

    public int getNumberOfPlants () {
        return worldMap.getNumberOfPlants();
    }

    public int getNumberOfUnoccupiedFields() {
        return worldMap.getNumberOfUnoccupiedPositions();
    }

    public int getTheMostPopularGeneAmongAliveAnimals () {
        List<Animal> aliveAnimals = worldMap.getAnimalList();
        int[] instancesOfGenome = {0,0,0,0,0,0,0,0};
        for (Animal animal : aliveAnimals) {
            for (int i : animal.getGenome()) {
                instancesOfGenome[i]++;
            }
        }
        int mostPopularGene = 0;
        for (int i = 0; i < 8; i++) {
            if (instancesOfGenome[i] > instancesOfGenome[mostPopularGene]) {
                mostPopularGene = i;
            }
        }
        return mostPopularGene;
    }

    public double getAverageLifetimeOfDeadAnimals () {
        double averageLifetime = 0;
        for (Animal animal : worldMap.getDeadAnimals()) {
            averageLifetime += animal.getLifeTime();
        }
        if (worldMap.getDeadAnimals().size() == 0) {
            return 0;
        }
        return averageLifetime/worldMap.getDeadAnimals().size();
    }

    public double getAverageNumberOfChildren () {
        double averageNumberOfChildren = 0;
        for (Animal animal : worldMap.getDeadAnimals()) {
            averageNumberOfChildren += animal.getNumberOfChildren();
        }
        for (Animal animal : worldMap.getAnimalList()) {
            averageNumberOfChildren += animal.getNumberOfChildren();
        }
        if (worldMap.getDeadAnimals().size()+worldMap.getAnimalList().size() == 0) {
            return 0;
        }
        return averageNumberOfChildren / (worldMap.getDeadAnimals().size()+worldMap.getAnimalList().size());
    }

    public double getAverageEnergy () {
        double averageEnergy = 0;
        for (Animal animal : worldMap.getAnimalList()) {
            averageEnergy += animal.getEnergy();
        }
        if (worldMap.getAnimalList().size() == 0) {
            return 0;
        }
        return (averageEnergy)/(worldMap.getAnimalList().size());
    }

}
