package util;
import model.Animal;

import java.util.ArrayList;
import java.util.Random;

public class GenesGiven {
    private final static int MINIMALMUTATIONCHANGES = 0;         //as for now
    private final static int MAXIMUMMUTATIONCHANGES = 3;         //as for now
    private final static boolean ACTIVESUBSTITUTION = false;     //as for now

    private static void mutationChange(ArrayList<Integer> childGenes) {
        Random rand = new Random();
        int indexToChange = rand.nextInt(childGenes.size());
        int newGene = rand.nextInt(8);
        while(newGene == childGenes.get(indexToChange)) {
            newGene = rand.nextInt(8);
        }
        childGenes.set(indexToChange, newGene);
    }

    private static void mutationSwap(ArrayList<Integer> childGenes) {
        Random rand = new Random();
        int firstIndex = rand.nextInt(childGenes.size());
        int secondIndex = rand.nextInt(childGenes.size());
        while(firstIndex == secondIndex) {
            secondIndex = rand.nextInt(childGenes.size());
        }
        int firstGene = childGenes.get(firstIndex);
        childGenes.set(firstIndex, childGenes.get(secondIndex));
        childGenes.set(secondIndex, firstGene);
    }

    public static ArrayList<Integer> createChildGenes(Animal firstParent, Animal secondParent) {
        Random rand = new Random();
        int firstParentEnergy = firstParent.getEnergy();
        int secondParentEnergy = secondParent.getEnergy();
        double placeOfDivision = (double) firstParentEnergy /(firstParentEnergy+secondParentEnergy);
        boolean strongerRightSite = rand.nextDouble() <= placeOfDivision;

        ArrayList<Integer> firstParentGenes = firstParent.getGenes();
        ArrayList<Integer> secondParentGenes = secondParent.getGenes();
        ArrayList<Integer> childGenes = new ArrayList<>();
        for(int i=0;i<firstParentGenes.size();i++) {
            if((strongerRightSite && i >= placeOfDivision * firstParentGenes.size()) || (!strongerRightSite && i < placeOfDivision * firstParentGenes.size())) {
                childGenes.add(secondParentGenes.get(i));
            }
            else {
                childGenes.add(firstParentGenes.get(i));
            }
        }

        int randomNumberOfMutations = rand.nextInt((MAXIMUMMUTATIONCHANGES - MINIMALMUTATIONCHANGES) + 1) + MINIMALMUTATIONCHANGES;
        for(int i=0;i<randomNumberOfMutations;i++) {
            mutationChange(childGenes);
            if (ACTIVESUBSTITUTION && rand.nextBoolean()) {
                mutationSwap(childGenes);
            }
        }
        return childGenes;
    }
}