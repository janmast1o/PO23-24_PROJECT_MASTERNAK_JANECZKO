package model;
import java.util.Comparator;
import java.util.Random;

public class ClusterComparator implements Comparator<Animal> {

    private Random random;

    public ClusterComparator () {
        random = new Random ();
    }

    public int compare (Animal firstAnimal, Animal secondAnimal) {

        if (firstAnimal.getEnergy() > secondAnimal.getEnergy()) {
            return 1;
        }

        else if (firstAnimal.getEnergy() < secondAnimal.getEnergy()) {
            return 0;
        }

        else if (firstAnimal.getDateOfBirth() < secondAnimal.getDateOfBirth()) {
            return 1;
        }

        else if (firstAnimal.getDateOfBirth() > secondAnimal.getDateOfBirth()) {
            return 0;
        }

        else if (firstAnimal.getNumberOfChildren() > secondAnimal.getNumberOfChildren()) {
            return 1;
        }

        else if (firstAnimal.getNumberOfChildren() < secondAnimal.getNumberOfChildren()) {
            return 0;
        }

        else {
            return random.nextInt(0,2);
        }
    }
}