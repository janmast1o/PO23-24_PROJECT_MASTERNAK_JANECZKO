package util;

import animal.Animal;
import javafx.geometry.Pos;
import model.Boundaries;
import model.Position;
import model.WorldMap;
import simulation.Simulation;
import simulation.SimulationRules;
import util.FisherYatesShuffle;

import java.util.ArrayList;
import java.util.Random;

public class SimulationInitializer {

    public static Simulation initialize (
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
            int numberOfPlantsGrownPerDay,
            String mutationMode,
            String plantGrowthMode
    ) {
        SimulationRules simulationRules = new SimulationRules (
                plantsNutritionalValue,
                sufficientReproductionEnergy,
                energyLostAfterReproduction,
                minNumberOfMutations,
                maxNumberOfMutations,
                numberOfPlantsGrownPerDay
        );
        WorldMap worldMap = new WorldMap (mapWidth,mapHeight);
        createStartingAnimals (worldMap,initialNumberOfAnimals,startingEnergy,lengthOfTheGenome);
        createStartingPlants (worldMap,initialNumberOfPlants,plantsNutritionalValue);
        return new Simulation (worldMap,simulationRules,mutationMode,plantGrowthMode);
    }

    private static ArrayList<Integer> createRandomGenome (int length) {
        Random random = new Random();
        ArrayList<Integer> randomGenome = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            randomGenome.add(random.nextInt(0,8));
        }
        return randomGenome;
    }

    private static void createStartingAnimals (WorldMap worldMap, int initialNumberOfAnimals, int startingEnergy, int lengthOfTheGenome) {
        for (int i=0; i<initialNumberOfAnimals; i++) {
            Animal newAnimal = new Animal (worldMap.getRandomPositionOnTheMap(), createRandomGenome(lengthOfTheGenome), startingEnergy);
            worldMap.placeAnimal(newAnimal.getPosition(), newAnimal);
        }
    }

    private static void createStartingPlants (WorldMap worldMap, int initialNumberOfPlants, int plantsNutritionalValue) {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        for (int i : FisherYatesShuffle.shuffle(initialNumberOfPlants, fieldsWithNoPlants.size())) {
            worldMap.growAPlant(fieldsWithNoPlants.get(i),plantsNutritionalValue);
        }
    }

}
