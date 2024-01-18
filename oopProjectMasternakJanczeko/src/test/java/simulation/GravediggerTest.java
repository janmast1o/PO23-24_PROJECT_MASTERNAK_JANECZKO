package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GravediggerTest {

    @Test
    void removeDeadAnimals() {
        //given
        WorldMap worldMap = new WorldMap(40,40);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(3,3),genome,30);
        Animal animal2 = new Animal(new Position(31,3),genome,3);
        Animal animal3 = new Animal(new Position(31,3),genome,3);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        animal1.drainEnergy(10);
        animal2.drainEnergy(3);
        animal3.drainEnergy(3);
        Gravedigger gravedigger = new Gravedigger(worldMap);
        gravedigger.removeDeadAnimals(0);
        List<Animal> aliveAnimals = worldMap.getAnimalList();
        List<Animal> deadAnimals = worldMap.getDeadAnimals();
        Set<Position> recentDeathPlaces = worldMap.getRecentDeathPlaces();

        //
        assertTrue(
                aliveAnimals.contains(animal1) &&
                !deadAnimals.contains(animal1) &&
                !aliveAnimals.contains(animal2) &&
                deadAnimals.contains(animal2) &&
                !aliveAnimals.contains(animal3) &&
                deadAnimals.contains(animal3) &&
                recentDeathPlaces.contains(new Position(31,3))
        );
        
    }
}