package simulation;

import model.WorldMap;
import util.FisherYatesShuffle;

import java.util.ArrayList;
import java.util.Random;

public class AlternativeReproductionManager extends AbstractReproductionManager {

    private Random geneMutator;

    public AlternativeReproductionManager (WorldMap worldMap) {
        super (worldMap);
        geneMutator = new Random();
    }

    private int getNumberToAdd () {
        if (geneMutator.nextBoolean()) return 1;
        else return -1;
    }

    @Override
    protected void mutate (ArrayList<Integer> genes, int minNumberOfMutations, int maxNumberOfMutations) {
        int chosenNumberOfMutations = geneMutator.nextInt(minNumberOfMutations,maxNumberOfMutations+1);
        //Fisher Yates Shuffle
        for (int i : FisherYatesShuffle.shuffle(chosenNumberOfMutations,genes.size())) {
            genes.set(i, (genes.get(i)+getNumberToAdd())%8);
            if (genes.get(i) == -1) genes.set(i,0);
        }
    }

}
