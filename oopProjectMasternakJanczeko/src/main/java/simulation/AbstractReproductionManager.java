package simulation;

import animal.Animal;
import model.Position;
import model.WorldMap;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractReproductionManager implements ReproductionManager {

    private WorldMap worldMap;

    private Random leftRightDecider;

    public AbstractReproductionManager(WorldMap worldMap) {
        leftRightDecider = new Random();
        this.worldMap = worldMap;
    }

    protected abstract void mutate (ArrayList<Integer> genes, int minNumberOfMutations, int maxNumberOfMutations);

    private ArrayList<Integer> createANewGenome (Animal firstAnimal, Animal secondAnimal, int minNumberOfMutations, int maxNumberOfMutations) {
        int genomeLength = firstAnimal.getGenomeSize();
        ArrayList<Integer> childGenes = new ArrayList<>(genomeLength);
        boolean fromTheLeft = leftRightDecider.nextBoolean();

        if (fromTheLeft) {
            int splitPoint = (int) ((genomeLength * firstAnimal.getEnergy())/(firstAnimal.getEnergy() + secondAnimal.getEnergy()));
            for (int i = 0; i < splitPoint; i++) {
                childGenes.add(firstAnimal.getGeneAtIndex(i));
            }

            for (int i = splitPoint; i < genomeLength; i++) {
                childGenes.add(secondAnimal.getGeneAtIndex(i));
            }
        }

        else {
            int splitPoint = (int) ((genomeLength * secondAnimal.getEnergy())/(firstAnimal.getEnergy() + secondAnimal.getEnergy()));
            for (int i = 0; i < splitPoint; i++) {
                childGenes.add(secondAnimal.getGeneAtIndex(i));
            }

            for (int i = splitPoint; i < genomeLength; i++) {
                childGenes.add(firstAnimal.getGeneAtIndex(i));
            }
        }

        mutate (childGenes,minNumberOfMutations,maxNumberOfMutations);
        return childGenes;
    }

    public void reproduceAnimals (int sufficientEnergy, int energyLostAfterReproduction, int minNumberOfMutations, int maxNumberOfMutatiions) {
        for (Position position : worldMap.getPositionsOfBigClusters()) {
            Animal firstAnimal = worldMap.getTopAnimalAt(position);
            Animal secondAnimal = worldMap.getSecondToTheTopAnimalAt(position);

            if (secondAnimal.hasSufficientEnergy(sufficientEnergy)) {
                ArrayList<Integer> newGenome = createANewGenome (firstAnimal, secondAnimal, minNumberOfMutations, maxNumberOfMutatiions);
                firstAnimal.drainEnergy(energyLostAfterReproduction);
                secondAnimal.drainEnergy(energyLostAfterReproduction);
                Animal child = new Animal (position,newGenome,2*energyLostAfterReproduction);
                worldMap.placeAnimal (position,child);
                firstAnimal.addChild(child);
                secondAnimal.addChild(child);
            }
        }
    }

}
