package simulation;

import model.Position;
import model.WorldMap;
import util.FisherYatesShuffle;
import util.PairOfLists;

import java.util.ArrayList;
import java.util.List;

public abstract class PlantGrowthManager {

    private WorldMap worldMap;

    public PlantGrowthManager (WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    protected abstract PairOfLists seperatePrefferedAndUnpreffred (List<Position> fieldsWithNoPlants, int equatorSpan);

    public void growPlants (int numberOfPlantsToGrow, int nutritionalValue, int equatorSpan) {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        PairOfLists lists = seperatePrefferedAndUnpreffred(fieldsWithNoPlants,equatorSpan);
        //preferred fields (80%)
        for (int i : FisherYatesShuffle.shuffle(Math.min((int) 0.8*numberOfPlantsToGrow, lists.firstList().size()), lists.firstList().size())) {
            worldMap.growAPlant(lists.firstList().get(i), nutritionalValue);
        }
        //unpreferred fields (20%)
        for (int i : FisherYatesShuffle.shuffle(Math.min((int) 0.2*numberOfPlantsToGrow, lists.secondList().size()), lists.secondList().size())) {
            worldMap.growAPlant(lists.secondList().get(i), nutritionalValue);
        }
    }

}
