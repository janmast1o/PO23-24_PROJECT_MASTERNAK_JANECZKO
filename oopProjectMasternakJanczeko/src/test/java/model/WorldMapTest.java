package model;

import animal.Animal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldMapTest {

    @Test
    void mapSize () {
        //given
        WorldMap worldMap = new WorldMap(4,4);

        //when & then
        assertTrue(worldMap.mapSize() == 16);
    }

    @Test
    void placeAnimal () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        List<Animal> animalList = worldMap.getAnimalList();

        //then
        assertTrue(animalList.contains(animal1) && animalList.contains(animal2) && animalList.contains(animal3));

    }

    @Test
    void removeAnimal () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.removeAnimal(animal1.getPosition(), animal1);
        worldMap.removeAnimal(animal2.getPosition(), animal2);
        List<Animal> animalList = worldMap.getAnimalList();

        //then
        assertTrue(!animalList.contains(animal1) && !animalList.contains(animal2) && animalList.contains(animal3));
    }

    @Test
    void moveAnimal () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.moveAnimal(animal1.getPosition(), animal1.previewMovement(), animal1);
        worldMap.moveAnimal(animal2.getPosition(), animal2.previewMovement(), animal2);
        List<Animal> animalList = worldMap.getAnimalList();

        //then
        assertTrue(animalList.contains(animal1) && animalList.contains(animal2) && animalList.contains(animal3));
    }

    @Test
    void getNumberOfUnoccupiedPositions () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);

        //then
        assertTrue (worldMap.getNumberOfUnoccupiedPositions() == 14);
    }

    @Test
    void growAPlant () {
        //given
        WorldMap worldMap = new WorldMap(4,4);

        //when
        worldMap.growAPlant(new Position(1,1), 5);
        worldMap.growAPlant(new Position(3,3), 6);

        //then
        assertTrue (worldMap.isOccupiedByPlants(new Position(1,1)) && worldMap.isOccupiedByPlants(new Position(3,3)) &&
                worldMap.getFieldsWithNoPlants().size() == 14 &&
                !worldMap.getFieldsWithNoPlants().contains(new Position(1,1)) &&
                !worldMap.getFieldsWithNoPlants().contains(new Position(3,3)));
    }

    @Test
    void removeAPlant () {
        //given
        WorldMap worldMap = new WorldMap(4,4);

        //when
        worldMap.growAPlant(new Position(1,1), 5);
        worldMap.growAPlant(new Position(3,3), 6);
        worldMap.removePlant(new Position(3,3));

        //then
        assertTrue (worldMap.isOccupiedByPlants(new Position(1,1)) && !worldMap.isOccupiedByPlants(new Position(3,3)) &&
                worldMap.getFieldsWithNoPlants().size() == 15 &&
                !worldMap.getFieldsWithNoPlants().contains(new Position(1,1)) &&
                worldMap.getFieldsWithNoPlants().contains(new Position(3,3)));
    }

    @Test
    void registerDeathOfAnAnimal () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,0);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.removeAnimal(animal3.getPosition(), animal3);
        worldMap.registerDeathOfAnAnimal(animal3.getPosition(), animal3);

        //then
        assertTrue (worldMap.getDeadAnimals().contains(animal3) && worldMap.getDeadAnimals().size() == 1 && worldMap.getRecentDeathPlaces().contains(animal3.getPosition()));
    }

    @Test
    void clearRecentDeathPlaces () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,0);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.removeAnimal(animal3.getPosition(), animal3);
        worldMap.registerDeathOfAnAnimal(animal3.getPosition(), animal3);
        worldMap.clearRecentDeathPlaces();

        //then
        assertTrue (worldMap.getDeadAnimals().contains(animal3) && worldMap.getDeadAnimals().size() == 1 && !worldMap.getRecentDeathPlaces().contains(animal3.getPosition()) && worldMap.getRecentDeathPlaces().isEmpty());
    }

    @Test
    void isOccupiedByAnimals () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);
        Animal animal4 = new Animal(new Position(3,3),genome,10);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.placeAnimal(animal4.getPosition(), animal4);
        worldMap.removeAnimal(animal4.getPosition(), animal4);

        //then
        assertTrue(worldMap.isOccupiedByAnimals(new Position(1,1)) && worldMap.isOccupiedByAnimals(new Position(2,2)) && !worldMap.isOccupiedByAnimals(new Position(3,3)));
    }

    @Test
    void getOccupiedBuBothAnimalsAndPlants () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(2,2),genome,2);
        Animal animal4 = new Animal(new Position(3,3),genome,10);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.placeAnimal(animal4.getPosition(), animal4);
        worldMap.growAPlant(new Position(1,1), 50);
        worldMap.growAPlant(new Position(3,3), 80);
        List<Position> occupiedByBothAnimalsAndPlants = worldMap.getOccupiedByBothAnimalsAndPlants();

        assertTrue (occupiedByBothAnimalsAndPlants.contains(new Position(1,1)) && occupiedByBothAnimalsAndPlants.contains(new Position(3,3)));
    }

    @Test
    void getPositionsOfBigClusters () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal(new Position(1,1),genome,100);
        Animal animal2 = new Animal(new Position(1,1),genome,50);
        Animal animal3 = new Animal(new Position(3,3),genome,2);
        Animal animal4 = new Animal(new Position(3,3),genome,10);
        Animal animal5 = new Animal(new Position(4,4),genome,70);

        //when
        worldMap.placeAnimal(animal1.getPosition(), animal1);
        worldMap.placeAnimal(animal2.getPosition(), animal2);
        worldMap.placeAnimal(animal3.getPosition(), animal3);
        worldMap.placeAnimal(animal4.getPosition(), animal4);
        worldMap.placeAnimal(animal5.getPosition(), animal5);
        List<Position> bigClusters = worldMap.getPositionsOfBigClusters();

        //then
        assertTrue (bigClusters.contains(new Position(1,1)) && bigClusters.contains(new Position(3,3)) && !bigClusters.contains(new Position(4,4)));
    }

    @Test
    void isMapEmpty () {
        //given
        WorldMap worldMap = new WorldMap(4,4);

        //when
        worldMap.growAPlant(new Position(2,1),900);

        //then
        assertTrue(worldMap.isEmpty());
    }

}