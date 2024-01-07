import model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        System.out.println("Beginning");
        Animal animal1 = new Animal(new Position(1,1), (ArrayList<Integer>) List.of(1,2,3));
        Animal animal2 = new Animal(new Position(1,1), (ArrayList<Integer>) List.of(1,2,3));
        System.out.println(animal1.getFacedSide());
        System.out.println(animal2.getFacedSide());
        animal1.move();
        animal2.move();
        System.out.println(animal1.getFacedSide());
        System.out.println(animal2.getFacedSide());
        animal1.move();
        animal2.move();
        System.out.println(animal1.getFacedSide());
        System.out.println(animal2.getFacedSide());
    }
}