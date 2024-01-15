package simulation;
public record SimulationRules (int plantNutritionalValue,
                               int sufficientReproductionEnergy,
                               int energyLostAfterReproduction,
                               int minNumberOfMutations,
                               int maxNumberOfMutations,
                               int numberOfNewPlantsPerDay,
                               int equatorSpan) {
}