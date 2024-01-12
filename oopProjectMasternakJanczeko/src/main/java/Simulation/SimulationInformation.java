package Simulation;

public record SimulationInformation (
        Integer plantNutritionalValue,
        int dailyNewPlants,
        int sufficientReproductionEnergy,
        int sufficientRepletionEnergy,
        int startingEnergy,
        int numberOfGenes,
        int minimalMutationChanges,
        int maximumMutationChanges,
        boolean activeSubsitution
)
{

}
