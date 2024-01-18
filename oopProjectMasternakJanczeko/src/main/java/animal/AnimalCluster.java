package animal;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AnimalCluster {

    private PriorityQueue<Animal> animals;

    public AnimalCluster () {
        animals = new PriorityQueue<>(Comparator.comparing(Animal :: getEnergy).
                                                 thenComparing(Animal :: getLifeTime).
                                                 thenComparing(Animal :: getNumberOfChildren).reversed());
    }

    public PriorityQueue<Animal> getCluster () {
        return animals;
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
        Animal secondToTheTopAnimal = animals.peek();
        animals.offer (topAnimal);
        return secondToTheTopAnimal;
    }

    public boolean areThereTwoAnimalsInCluster() {
        return animals.size() >= 2;
    }

    public boolean isEmpty () {
        return animals.size() == 0;
    }

    public String toString () {
        return getTopAnimal().toString();
    }

    public List<Animal> getClusterAsList () {
        List<Animal> animalList = new LinkedList<>();
        for (Animal animal : animals) {
            animalList.add(animal);
        }
        return animalList;
    }



}
