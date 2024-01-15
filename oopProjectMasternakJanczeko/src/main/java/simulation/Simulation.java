package simulation;

import animal.Animal;
import model.WorldMap;

import java.util.LinkedList;

public class Simulation implements Runnable {

    private int day;

    private WorldMap worldMap;

    private LinkedList<Animal> deadAnimals;

    private Gravedigger gravedigger;

    private AnimalGuide animalGuide;

    private PlantConsumptionManager plantConsumptionManager;

    private ReproductionManager reproductionManager;

    private PlantGrowthManager plantGrowthManager;

    private SimulationRules simulationRules;

    public Simulation (WorldMap worldMap, SimulationRules simulationRules, String mutationMode, String plantGrowthMode) {
        day = 1;
        deadAnimals = new LinkedList<>();
        this.worldMap = worldMap;
        this.simulationRules = simulationRules;

        gravedigger = new Gravedigger(worldMap);
        animalGuide = new AnimalGuide(worldMap);
        plantConsumptionManager = new PlantConsumptionManager(worldMap);

        if (mutationMode.equals("default")) {
            reproductionManager = new DeafultReproductionManager(worldMap);
        }
        else {
            reproductionManager = new AlternativeReproductionManager(worldMap);
        }

        if (plantGrowthMode.equals("default")) {
            plantGrowthManager = new DefaultPlantGrowthManager(worldMap);
        }
        else {
            plantGrowthManager = new DefaultPlantGrowthManager(worldMap); //to be changed
        }

    }

    public void run () {
        while (!worldMap.isMapEmpty()) {

            gravedigger.removeDeadAnimals(day);

            animalGuide.moveAnimals();

            plantConsumptionManager.consumePlants(simulationRules.plantNutritionalValue());

            reproductionManager.reproduceAnimals(simulationRules.sufficientReproductionEnergy(),
                                                 simulationRules.energyLostAfterReproduction(),
                                                 simulationRules.minNumberOfMutations(),
                                                 simulationRules.maxNumberOfMutations());

            plantGrowthManager.growPlants(simulationRules.numberOfNewPlantsPerDay(),
                                          simulationRules.plantNutritionalValue(),
                                          simulationRules.equatorSpan()); //!

            for (Animal animal : worldMap.getAnimalList()) {
                animal.ageByADay();
            }

            day++;
        }

    }

}
