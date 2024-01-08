package Simulation;

import model.Boundaries;

public record SimulationInformation (
        Integer plantNutritionalValue,
        int dailyNewPlants,
        int sufficientReproductionEnergy,
        int sufficientRepletionEnergy,
        int startingEnergy,
        int numberOfGenes
)
{}
