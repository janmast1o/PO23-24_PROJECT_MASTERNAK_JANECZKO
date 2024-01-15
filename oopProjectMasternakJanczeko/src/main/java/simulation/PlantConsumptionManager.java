package simulation;
import model.Position;
import model.WorldMap;

public class PlantConsumptionManager {

    private WorldMap worldMap;

    protected PlantConsumptionManager (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected void consumePlants (int plantNutritionalValue) {
        for (Position position : worldMap.getOccupiedByBothAnimalsAndPlants()) {
            worldMap.getTopAnimalAt(position).replenishEnergy(plantNutritionalValue);
            worldMap.getTopAnimalAt(position).registerPlantConsumption();
            worldMap.removePlant(position);
        }
    }
}