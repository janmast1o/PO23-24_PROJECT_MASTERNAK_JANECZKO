package simulation;

import animal.Animal;
import model.Position;
import model.WorldDirection;
import model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAnimalGuideTest {

    @Test
    void moveAnimalBreachYBorderN0 () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(0);
        while (true) {
            animal = new Animal(new Position(3,4),genome,3);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.SOUTH) &&
                animal.getPosition().equals(new Position(3,4)) &&
                worldMap.getTopAnimalAt(new Position(3,4)).equals(animal));

    }

    @Test
    void moveAnimalBreachBorderN1 () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(1);
        while (true) {
            animal = new Animal(new Position(3,4),genome,3);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.SOUTHWEST) &&
                animal.getPosition().equals(new Position(3,4)) &&
                worldMap.getTopAnimalAt(new Position(3,4)).equals(animal));
    }

    @Test
    void moveAnimalBreachBordersN7 () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        while (true) {
            animal = new Animal(new Position(3,4),genome,3);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.SOUTHEAST) &&
                animal.getPosition().equals(new Position(3,4)) &&
                worldMap.getTopAnimalAt(new Position(3,4)).equals(animal));
    }

    @Test
    void moveAnimalBreachYBorderNE0 () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(0);
        while (true) {
            animal = new Animal(new Position(3,4),genome,3);
            if (animal.getDirection().equals(WorldDirection.NORTHEAST)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.SOUTHWEST) &&
                animal.getPosition().equals(new Position(3,4)) &&
                worldMap.getTopAnimalAt(new Position(3,4)).equals(animal));
    }

    @Test
    void moveAnimalBreachYBorderS0 () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(0);
        while (true) {
            animal = new Animal(new Position(3,0),genome,3);
            if (animal.getDirection().equals(WorldDirection.SOUTH)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.NORTH) &&
                animal.getPosition().equals(new Position(3,0)) &&
                worldMap.getTopAnimalAt(new Position(3,0)).equals(animal));
    }

    @Test
    void moveAnimalBreachXBorderE () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(0);
        while (true) {
            animal = new Animal(new Position(3,2),genome,3);
            if (animal.getDirection().equals(WorldDirection.EAST)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.EAST) &&
                animal.getPosition().equals(new Position(0,2)) &&
                worldMap.getTopAnimalAt(new Position(0,2)).equals(animal));
    }

    @Test
    void moveAnimalBreachXBorderNE () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        while (true) {
            animal = new Animal(new Position(3,2),genome,3);
            if (animal.getDirection().equals(WorldDirection.EAST)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.NORTHEAST) &&
                animal.getPosition().equals(new Position(0,3)) &&
                worldMap.getTopAnimalAt(new Position(0,3)).equals(animal));
    }

    @Test
    void moveAnimalBreachXBorderW () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(1);
        while (true) {
            animal = new Animal(new Position(0,2),genome,3);
            if (animal.getDirection().equals(WorldDirection.WEST)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.NORTHWEST) &&
                animal.getPosition().equals(new Position(3,3)) &&
                worldMap.getTopAnimalAt(new Position(3,3)).equals(animal));
    }

    @Test
    void moveAnimalBreachXAndYBorder () {
        //given
        WorldMap worldMap = new WorldMap(4,4);
        Animal animal;
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(4);
        while (true) {
            animal = new Animal(new Position(3,3),genome,3);
            if (animal.getDirection().equals(WorldDirection.SOUTHWEST)) break;
        }

        //when
        worldMap.placeAnimal(animal.getPosition(), animal);
        AnimalGuide animalGuide = new DefaultAnimalGuide(worldMap);
        animalGuide.moveAnimals();

        //then
        assertTrue (animal.getDirection().equals(WorldDirection.SOUTHWEST) &&
                animal.getPosition().equals(new Position(3,3)) &&
                worldMap.getTopAnimalAt(new Position(3,3)).equals(animal));
    }

}