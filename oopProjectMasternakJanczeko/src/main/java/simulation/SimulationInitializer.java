package simulation;

import animal.Animal;
import javafx.geometry.Pos;
import model.Boundaries;
import model.Position;
import model.WorldMap;
import util.FisherYatesShuffle;

import java.util.ArrayList;
import java.util.Random;

public class SimulationInitializer {

    private SimulationRules simulationRules;

    private WorldMap worldMap;

    public SimulationInitializer (
            int mapWidth,
            int mapHeight,
            int initialNumberOfPlants,
            int initialNumberOfAnimals,
            int startingEnergy,
            int plantsNutritionalValue,
            int sufficientReproductionEnergy,
            int lengthOfTheGenome,
            int minNumberOfMutations,
            int maxNumberOfMutations,
            int energyLostAfterReproduction,
            int equatorSpan,
            int numberOfPlantsGrownPerDay
    ) {
        simulationRules = new SimulationRules (
                plantsNutritionalValue,
                sufficientReproductionEnergy,
                energyLostAfterReproduction,
                minNumberOfMutations,
                maxNumberOfMutations,
                numberOfPlantsGrownPerDay,
                equatorSpan
        );
        worldMap = new WorldMap (new Boundaries (new Position (0,0), new Position (mapWidth-1,mapHeight-1)));
        createStartingAnimals (initialNumberOfAnimals,startingEnergy,lengthOfTheGenome);
        createStartingPlants (initialNumberOfPlants,plantsNutritionalValue);

    }

    private ArrayList<Integer> createRandomGenome (int length) {
        Random random = new Random();
        ArrayList<Integer> randomGenome = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            randomGenome.add(random.nextInt(0,8));
        }
        return randomGenome;
    }

    private void createStartingAnimals (int initialNumberOfAnimals, int startingEnergy, int lengthOfTheGenome) {
        for (int i=0; i<initialNumberOfAnimals; i++) {
            Animal newAnimal = new Animal (worldMap.getRandomPositionOnTheMap(), createRandomGenome(lengthOfTheGenome), startingEnergy);
            worldMap.placeAnimal(newAnimal.getPosition(), newAnimal);
        }
    }

    private void createStartingPlants (int initialNumberOfPlants, int plantsNutritionalValue) {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        for (int i : FisherYatesShuffle.shuffle(initialNumberOfPlants, fieldsWithNoPlants.size())) {
            worldMap.growAPlant(fieldsWithNoPlants.get(i),plantsNutritionalValue);
        }
    }

    public Simulation initializeSimulation () {
        return new Simulation (worldMap,simulationRules,"default","default");
    }

}
