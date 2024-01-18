package simulation;

import model.Position;
import model.WorldMap;
import util.FisherYatesShuffle;
import util.PairOfLists;

import java.util.*;

public abstract class AbstractPlantGrowthManager implements PlantGrowthManager {

    private WorldMap worldMap;

    public AbstractPlantGrowthManager(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public WorldMap getWorldMap () {
        return worldMap;
    }

    protected abstract PairOfLists seperatePrefferedAndUnpreffred (List<Position> fieldsWithNoPlants);

    public void growPlants (int numberOfPlantsToGrow, int nutritionalValue) {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        PairOfLists lists = seperatePrefferedAndUnpreffred(fieldsWithNoPlants);
        int count = Math.min (numberOfPlantsToGrow, lists.firstList().size());
        int splitPoint = (int) (count*0.8);
        List<Integer> preferredFieldsChoice = FisherYatesShuffle.shuffle (splitPoint,count);
        List<Integer> unpreferredFieldsChoice = FisherYatesShuffle.shuffle (count-splitPoint,count);

        for (int i : preferredFieldsChoice) {
            worldMap.growAPlant(lists.firstList().get(i), nutritionalValue);
        }
        for (int i : unpreferredFieldsChoice) {
            worldMap.growAPlant(lists.secondList().get(i), nutritionalValue);
        }
    }

    @Override
    public Set<Position> getPreferredFields() {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        PairOfLists lists = seperatePrefferedAndUnpreffred(fieldsWithNoPlants);
        return new HashSet<>(lists.firstList());
    }
}
