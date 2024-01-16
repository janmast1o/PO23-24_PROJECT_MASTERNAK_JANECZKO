package simulation;

import animal.Animal;
import javafx.stage.Stage;
import model.WorldMap;
import presenter.SimulationApp;

import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable {

    private int day;

    private WorldMap worldMap;

    private LinkedList<Animal> deadAnimals;

    private GraveDigger gravedigger;

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

        gravedigger = new GraveDigger(worldMap);
        animalGuide = new AnimalGuide(worldMap);
        plantConsumptionManager = new PlantConsumptionManager(worldMap);

        if (mutationMode.equals("default")) {
            reproductionManager = new DefaultReproductionManager(worldMap);
        }
        else {
            reproductionManager = new AlternativeReproductionManager(worldMap);
        }

        if (plantGrowthMode.equals("default")) {
            plantGrowthManager = new DefaultPlantGrowthManager(worldMap);
        }
        else {
            plantGrowthManager = new DefaultPlantGrowthManager(worldMap); //to be added
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
                                          simulationRules.equatorSpan()); //!!!!!

            System.out.println("run before getAnimalsList");
            for (Animal animal : worldMap.getAnimalList()) {
                System.out.println("run after getAnimalsList");
                animal.ageByADay();
            }

            day++;
        }
        try {
            SimulationApp simulationApp = new SimulationApp();
            Stage stage = new Stage();      // TODO - pass proper stage
            simulationApp.start(stage);
        } catch (Exception e) {
            System.out.println("error :( " + e.getClass().getName() + " " + e.getMessage() + " " + List.of(e.getStackTrace()));
//            throw new RuntimeException(e);
        }
        finally {
            System.out.println("SimulationApp finished");
        }
    }

}