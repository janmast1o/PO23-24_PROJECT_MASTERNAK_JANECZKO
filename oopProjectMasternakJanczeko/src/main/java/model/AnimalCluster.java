package model;
import java.util.*;

public class AnimalCluster {
    private Comparator<Animal> comparator;

    private PriorityQueue<Animal> animals;

    public AnimalCluster () {
        this.comparator = new ClusterComparator();
        this.animals = new PriorityQueue<>(comparator);
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

    public int size () {
        return this.size();
    }

    public boolean fitForReporoduction (int sufficientReproductionEnergy) {
        return size() > 1 && getSecondToTheTopAnimal().getEnergy() > sufficientReproductionEnergy;
    }

}