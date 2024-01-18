package util;

import animal.Animal;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDescendantsFinder {

    public static int findNumberOfDescendants (Animal animal) {
        Set<Animal> descendants = new HashSet<>();
        descendants.add(animal);
        int numberOfDescendants = 0;
        for (Animal child : animal.getChildren()) {
            numberOfDescendants += getNumberOfDescendantsOfAnAnimal(child,descendants)+1;
        }
        return numberOfDescendants;
    }

    private static int getNumberOfDescendantsOfAnAnimal (Animal animal, Set<Animal> alreadySeen) {
        int numberOfDescendants = 0;
        alreadySeen.add(animal);
        for (Animal child : animal.getChildren()) {
            if (!alreadySeen.contains(child)) {
                numberOfDescendants += getNumberOfDescendantsOfAnAnimal(child,alreadySeen)+1;
            }
        }
        return numberOfDescendants;
    }

}
