package simulation;

import model.WorldMap;
import util.FisherYatesShuffle;

import java.util.ArrayList;
import java.util.Random;

public class DeafultReproductionManager extends AbstractReproductionManager {

    private Random geneMutator;

    public DeafultReproductionManager (WorldMap worldMap) {
        super (worldMap);
        geneMutator = new Random();
    }

    @Override
    protected void mutate (ArrayList<Integer> genes, int minNumberOfMutations, int maxNumberOfMutations) {
        int chosenNumberOfMutations = geneMutator.nextInt(minNumberOfMutations,maxNumberOfMutations+1);
        //Fisher Yates Shuffle
        for (int geneIndex : FisherYatesShuffle.shuffle(chosenNumberOfMutations,genes.size())) {
            genes.set (geneIndex,geneMutator.nextInt(0,8));
        }
    }
}
