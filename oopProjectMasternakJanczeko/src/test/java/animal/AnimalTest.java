package animal;

import model.Position;
import model.WorldDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void previewMovement () {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal;

        while (true) {
            animal = new Animal(new Position(1, 1), genome, 100);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        Position previewedMovement = animal.previewMovement();

        //then
        assertTrue (previewedMovement.equals(new Position (0,2)));

    }

    @Test
    void move () {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal;

        while (true) {
            animal = new Animal(new Position(1, 1), genome, 100);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        animal.move();

        //then
        assertTrue (animal.getPosition().equals(new Position (0,2)) && animal.getDirection().equals(WorldDirection.NORTHWEST));

    }

    @Test
    void turnAround () {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal;
        while (true) {
            animal = new Animal(new Position(1, 1), genome, 100);
            if (animal.getDirection().equals(WorldDirection.NORTH)) break;
        }

        //when
        animal.turnAround();

        //then
        assertTrue (animal.getPosition().equals(new Position (1,1)) && animal.getDirection().equals(WorldDirection.SOUTH));
    }

    @Test
    void child() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal (new Position (0,0), genome, 100);

        //when
        Animal child = new Animal (new Position (0,0), genome, 100);
        animal.addChild(child);

        //then
        assertTrue (animal.getNumberOfChildren() == 1 && animal.getChildren().contains(child));
    }

    @Test
    void isAlive() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal (new Position (0,0), genome, 100);
        Animal animal2 = new Animal (new Position (0,0), genome, 100);

        //when
        animal1.drainEnergy(20);
        animal2.drainEnergy(100);

        //then
        assertTrue (animal1.isAlive() && !animal2.isAlive());
    }

    @Test
    void plantConsumption() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal (new Position (0,0), genome, 100);

        //when
        animal.registerPlantConsumption();
        animal.registerPlantConsumption();
        animal.registerPlantConsumption();

        //then
        assertTrue(animal.getNumberOfEatenPlants() == 3);

    }

    @Test
    void replenishEnergy() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal (new Position (0,0), genome, 100);

        //when
        animal.replenishEnergy(30);
        animal.replenishEnergy(30);

        //then
        assertTrue(animal.getEnergy() == 160);
    }

    @Test
    void drainEnergy() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal (new Position (0,0), genome, 100);

        //when
        animal.drainEnergy(30);
        animal.drainEnergy(30);

        //then
        assertTrue(animal.getEnergy() == 40);
    }

    @Test
    void hasSufficientEnergy() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal1 = new Animal (new Position (0,0), genome, 100);
        Animal animal2 = new Animal (new Position (0,0), genome, 100);

        //when & then
        assertTrue (animal1.hasSufficientEnergy(100) && !animal2.hasSufficientEnergy(101));
    }

    @Test
    void ageByADay() {
        //given
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal (new Position (0,0), genome, 100);

        //when
        animal.ageByADay();
        animal.ageByADay();
        animal.ageByADay();

        //then
        assertTrue (animal.getLifeTime() == 3);
    }
}