package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPlantConsumptionManagerTest {

    @Test
    void consumePlants() {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(0,2),genome,4);
        Animal animal3 = new Animal(new Position(0,2),genome,1);
        Animal animal2 = new Animal(new Position(3,0),genome,40);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.growAPlant(new Position(0,2),1);
        worldMap.growAPlant(new Position(3,0),40);
        worldMap.growAPlant(new Position(3,3), 1);
        PlantConsumptionManager plantConsumptionManager = new DefaultPlantConsumptionManager(worldMap);
        plantConsumptionManager.consumePlants();

        assertTrue (
                !worldMap.isOccupiedByPlants(new Position(0,2)) &&
                        !worldMap.isOccupiedByPlants(new Position(3,0)) &&
                        worldMap.isOccupiedByPlants(new Position(3,3)) &&
                        worldMap.getNumberOfPlants() == 1 &&
                        animal1.getEnergy() == 5 &&
                        animal2.getEnergy() == 80 &&
                        animal3.getEnergy() == 1 &&
                        animal1.getNumberOfEatenPlants() == 1 &&
                        animal2.getNumberOfEatenPlants() == 1 &&
                        animal3.getNumberOfEatenPlants() == 0
        );

    }
}