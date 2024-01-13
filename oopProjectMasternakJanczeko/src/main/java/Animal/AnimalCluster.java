package Animal;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AnimalCluster {

    private PriorityQueue<Animal> animals;

    public AnimalCluster () {
        animals = new PriorityQueue<>(Comparator.comparing(Animal :: getEnergy).reversed().
                                                 thenComparing(Animal :: getBirth).
                                                 thenComparing(Animal :: getNumberOfChildren).reversed());
    }

    public void addAnimal (Animal animal) {
        animals.offer (animal);
    }

    public void removeAnimal (Animal animal) {
        animals.remove (animal);
    }

    public Animal getTopAnimal () {
        return animals.peek();
    }

    public Animal getSecondToTheTopAnimal () {
        Animal topAnimal = animals.poll();
        Animal secondToTheTopAnimal = animals.poll();
        animals.offer (topAnimal);
        return secondToTheTopAnimal;
    }

    public boolean areThereTwoAnimalsInCluster() {
        return animals.size() >= 2;
    }

    public boolean isEmpty () {
        return animals.size() == 0;
    }



}
