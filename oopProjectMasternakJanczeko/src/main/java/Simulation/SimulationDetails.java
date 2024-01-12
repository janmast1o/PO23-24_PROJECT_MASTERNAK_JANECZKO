package Simulation;

import model.Animal;

public class SimulationDetails  {

    private Simulation simulation;

    public SimulationDetails (Simulation simulation) {
        this.simulation = simulation;
    }

    public int getNumberOfAliveAnimals () {
        int numberOfAliveAnimals = 0;
        for (Animal animal : simulation.getAnimals()) {
            if (animal.getDateOfDeath() == -1) {
                numberOfAliveAnimals++;
            }
        }
        return numberOfAliveAnimals;
    }

    public int getNumberOfPlants () {
        return simulation.getMap().getPlants().size();
    }


}
