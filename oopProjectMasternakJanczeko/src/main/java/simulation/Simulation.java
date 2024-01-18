package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;
import presenter.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public class Simulation implements Runnable {

    private int day;

    private boolean stopped;

    private WorldMap worldMap;

    private Gravedigger gravedigger;

    private AnimalGuide animalGuide;

    private PlantConsumptionManager plantConsumptionManager;

    private ReproductionManager reproductionManager;

    private PlantGrowthManager plantGrowthManager;

    private SimulationRules simulationRules;

    private SimulationInformation simulationInformation;

    private SimulationObserver simulationObserver;

    private Animal animalToTrack;

    public Simulation (WorldMap worldMap, SimulationRules simulationRules, String mutationMode, String plantGrowthMode) {
        day = 1;
        stopped = false;
        simulationInformation = new SimulationInformation(worldMap);
        this.worldMap = worldMap;
        this.simulationRules = simulationRules;

        gravedigger = new Gravedigger(worldMap);
        animalGuide = new DefaultAnimalGuide(worldMap);
        plantConsumptionManager = new DefaultPlantConsumptionManager(worldMap);

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
            plantGrowthManager = new AlternativePlantGrowthManager(worldMap);
        }

    }

    public WorldMap getWorldMap () {
        return worldMap;
    }

    public void setMapChangeListener (MapChangeListener listener) {
        worldMap.addListener(listener);
    }

    public void setSimulationObserver (SimulationObserver observer) {
        simulationObserver = observer;
    }

    public boolean isStopped () {
        return stopped;
    }

    public void stop () {
        stopped = true;
        animalGuide.stop();
    }

    public void resume () {
        stopped = false;
        animalGuide.resume();
        synchronized (this) {
            this.notify();
        }
    }

    public void startTracking (Position position, SimulationPresenter simulationPresenter) {
        if (animalToTrack != null) {
            animalToTrack.removeTracker();
        }
        try {
            animalToTrack = worldMap.getTopAnimalAt(position);
            animalToTrack.setTracker(new UIAnimalTracker(simulationPresenter));
        }
        catch (NullPointerException exception) {
            ;
        }
    }

    public void stopTracking () {
        if (animalToTrack != null) {
            animalToTrack.removeTracker();
        }
    }

    public void run () {
        while (!worldMap.isEmpty()) {

            gravedigger.removeDeadAnimals(day);

            animalGuide.moveAnimals();

            plantConsumptionManager.consumePlants();

            reproductionManager.reproduceAnimals(simulationRules.sufficientReproductionEnergy(),
                                                 simulationRules.energyLostAfterReproduction(),
                                                 simulationRules.minNumberOfMutations(),
                                                 simulationRules.maxNumberOfMutations());

            plantGrowthManager.growPlants(simulationRules.numberOfNewPlantsPerDay(),
                                          simulationRules.plantNutritionalValue()); //!

            worldMap.clearRecentDeathPlaces();

            for (Animal animal : worldMap.getAnimalList()) {
                animal.ageByADay();
            }

            if (simulationObserver != null) {
                simulationObserver.simulationChanged(
                        simulationInformation.getNumberOfAnimals(),
                        simulationInformation.getNumberOfPlants(),
                        simulationInformation.getTheMostPopularGeneAmongAliveAnimals(),
                        simulationInformation.getAverageEnergy(),
                        simulationInformation.getAverageLifetimeOfDeadAnimals(),
                        simulationInformation.getAverageNumberOfChildren(),
                        day,
                        simulationInformation.getNumberOfUnoccupiedFields()
                );
            }

            day++;

            {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (this) {
                    while (stopped) {
                        try {
                            this.wait();
                        } catch (
                                InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
