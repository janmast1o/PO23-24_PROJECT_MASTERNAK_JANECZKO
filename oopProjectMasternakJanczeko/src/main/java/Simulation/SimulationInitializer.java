package Simulation;

import model.Animal;
import model.Boundaries;
import model.Position;
import model.WorldMap;
import util.RandomPositionGenerator;

import java.util.*;

public class SimulationInitializer {

    private SimulationInformation information;

    private Simulation simulation;

    private SimulationInitializer (
            int height,
            int width,
            Integer plantNutritionalValue,
            int dailyNewPlants,
            int sufficientReproductionEnergy,
            int sufficientRepletionEnergy,
            int startingEnergy,
            int numberOfGenes,
            int numberOfStartingAnimals,
            int numberOfStartingPlants,
            int minimalMutationChanges,
            int maximumMutationChanges,
            boolean activeSubstitution
    ) {
        information = new SimulationInformation (
                plantNutritionalValue,
                dailyNewPlants,
                sufficientReproductionEnergy,
                sufficientRepletionEnergy,
                startingEnergy,
                numberOfGenes,
                minimalMutationChanges,
                maximumMutationChanges,
                activeSubstitution
        );
        WorldMap worldMap = new WorldMap (new Boundaries (new Position (0,0), new Position (height,width)) );
        LinkedList<Animal> startingAnimals = startingAnimals (height,width,numberOfStartingAnimals,worldMap);
        startingPlants (height,width,numberOfStartingPlants,worldMap);
        simulation = new Simulation (worldMap,startingAnimals,information);

    }

    public LinkedList<Animal> startingAnimals (int height, int width, int numberOfStartingAnimals, WorldMap worldMap) {
        Random random = new Random();
        LinkedList<Animal> startingAnimals = new LinkedList<>();
        Animal animal;

        for (int i = 0; i< numberOfStartingAnimals; i++) {
            animal = new Animal (
                    0,
                    new Position (random.nextInt(0,width), random.nextInt(0,height)),
                    startingAnimalGenes(information.numberOfGenes()),
                    information.startingEnergy());
            startingAnimals.add (animal);
            worldMap.placeAnimal (animal);
        }

        return startingAnimals;

    }

    private ArrayList<Integer> startingAnimalGenes (int n) {
        Random random = new Random();
        ArrayList<Integer> genes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            genes.set (i, random.nextInt(0,8));
        }

        return genes;
    }

    public void startingPlants (int width, int height, int numberOfStartingPlants, WorldMap worldMap) {
        List<Position> startingPlants = new LinkedList<>();
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator (width,height,numberOfStartingPlants);
        Iterator<Position> iterator = randomPositionGenerator.iterator();
        Position position;
        while (iterator.hasNext()) {
            position = iterator.next ();
            startingPlants.add (position);
            worldMap.growAPlant (position,information.plantNutritionalValue());
        }

    }

}