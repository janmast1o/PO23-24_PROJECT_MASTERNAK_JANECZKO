import model.*;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        System.out.println("Beginning");
        Animal animal = new Animal(new Position(1,1),List.of(1,2,3));
        System.out.println(animal.getFacedSide());
        animal.move();
        System.out.println(animal.getFacedSide());
        animal.move();
        System.out.println(animal.getFacedSide());
        animal.move();
        System.out.println(animal.getFacedSide());
        animal.move();
        System.out.println(animal.getFacedSide());
    }
}