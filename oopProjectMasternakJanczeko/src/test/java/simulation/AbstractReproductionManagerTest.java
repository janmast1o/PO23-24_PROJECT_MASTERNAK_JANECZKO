package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractReproductionManagerTest {

    @Test
    void reproduceAnimals() {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        genome.add(5);
        genome.add(0);
        Animal animal1 = new Animal(new Position(1,1),genome,400);
        Animal animal2 = new Animal(new Position(1,1),genome,200);
        Animal animal3 = new Animal(new Position(1,1),genome,30);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        ReproductionManager reproductionManager = new DeafultReproductionManager(worldMap);
        reproductionManager.reproduceAnimals(20,4,1,2);

        List<Animal> animalList = worldMap.getAnimalList();

        //then
        assertTrue (
                animal1.getEnergy() == 396 &&
                        animal2.getEnergy() == 196 &&
                        animal3.getEnergy() == 30 &&
                        animal1.getNumberOfChildren() == 1 &&
                        animal2.getNumberOfChildren() == 1 &&
                       animal3.getNumberOfChildren() == 0 &&
                        animalList.contains(animal1) && animalList.contains(animal2) && animalList.contains(animal3) &&
                       animalList.size() == 4
        );

    }
}