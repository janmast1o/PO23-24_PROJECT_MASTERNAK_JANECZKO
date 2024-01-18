package simulation;

import model.Position;
import model.WorldMap;

public class DefaultPlantConsumptionManager implements  PlantConsumptionManager{

    private WorldMap worldMap;

    protected DefaultPlantConsumptionManager(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void consumePlants () {
        for (Position position : worldMap.getOccupiedByBothAnimalsAndPlants()) {
            worldMap.getTopAnimalAt(position).replenishEnergy(worldMap.getPlantsNutritionalValueAt(position));
            worldMap.getTopAnimalAt(position).registerPlantConsumption();
            worldMap.removePlant(position);
        }
    }

}
