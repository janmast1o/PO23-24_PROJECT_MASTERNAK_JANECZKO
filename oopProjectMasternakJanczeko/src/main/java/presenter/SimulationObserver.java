package presenter;

public interface SimulationObserver {

    void simulationChanged (int numberOfAnimals, int numberOfPlants, int mostPopularGene, double averageEnergy, double averageLifetime, double averageNumberOfChildren, int day, int numberOfUnoccupiedFields);

}
