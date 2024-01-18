package animal;

import model.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnimalClusterTest {

    @Test
    void getTopAnimal () {
        //given
        AnimalCluster animalCluster = new AnimalCluster();
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);

        Animal animal1 = new Animal (new Position(0,0), genome, 150);
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.addChild (new Animal (new Position(0,0),genome,2));
        animal1.addChild (new Animal (new Position(0,0),genome,2));

        Animal animal2 = new Animal (new Position(0,0), genome, 150);
        animal2.ageByADay();
        animal2.ageByADay();
        animal2.ageByADay();
        animal2.addChild (new Animal (new Position(0,0),genome,2));

        Animal animal3 = new Animal (new Position(0,0), genome, 150);
        animal3.ageByADay();
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));

        //when
        animalCluster.addAnimal(animal1);
        animalCluster.addAnimal(animal2);
        animalCluster.addAnimal(animal3);

        //then
        assertTrue (animalCluster.getTopAnimal().equals(animal1));

    }

    @Test
    void getSecondToTheTopAnimal () {
        //given
        AnimalCluster animalCluster = new AnimalCluster();
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);

        Animal animal1 = new Animal (new Position(0,0), genome, 150);
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.ageByADay();
        animal1.addChild (new Animal (new Position(0,0),genome,2));
        animal1.addChild (new Animal (new Position(0,0),genome,2));

        Animal animal2 = new Animal (new Position(0,0), genome, 150);
        animal2.ageByADay();
        animal2.ageByADay();
        animal2.ageByADay();
        animal2.addChild (new Animal (new Position(0,0),genome,2));

        Animal animal3 = new Animal (new Position(0,0), genome, 150);
        animal3.ageByADay();
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));
        animal3.addChild (new Animal (new Position(0,0),genome,2));

        //when
        animalCluster.addAnimal(animal1);
        animalCluster.addAnimal(animal2);
        animalCluster.addAnimal(animal3);

        //then
        assertTrue (animalCluster.getSecondToTheTopAnimal().equals(animal2));
    }


    @Test
    void isEmpty () {
        //given
        AnimalCluster animalCluster = new AnimalCluster();

        //when & then
        assertTrue (animalCluster.isEmpty());
    }

    @Test
    void addAnimal () {
        //given
        AnimalCluster animalCluster = new AnimalCluster();
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal(new Position(0,0),genome,150);

        //when
        animalCluster.addAnimal(animal);

        //then
        assertTrue (animalCluster.getCluster().contains(animal));
    }

    @Test
    void removeAnimal () {
        //given
        AnimalCluster animalCluster = new AnimalCluster();
        ArrayList<Integer> genome = new ArrayList<>();
        genome.add(7);
        Animal animal = new Animal(new Position(0,0),genome,150);

        //when
        animalCluster.addAnimal(animal);
        animalCluster.removeAnimal(animal);

        //then
        assertFalse (animalCluster.getCluster().contains(animal));
    }

}