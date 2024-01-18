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
        int count = Math.min (numberOfPlantsToGrow, fieldsWithNoPlants.size());

        List<Integer> preferredFieldsChoice = FisherYatesShuffle.shuffle (
                lists.firstList().size(), lists.firstList().size()
        );
        List<Integer> unpreferredFieldsChoice = FisherYatesShuffle.shuffle (
                lists.secondList().size(), lists.secondList().size()
        );

        int i=0;
        while (i < (count*0.8) && i < lists.firstList().size()) {
            worldMap.growAPlant(lists.firstList().get(preferredFieldsChoice.get(i)), nutritionalValue);
            i++;
        }
        int j=0;
        while (i < count && j < lists.secondList().size()) {
            worldMap.growAPlant(lists.secondList().get(unpreferredFieldsChoice.get(j)), nutritionalValue);
            i++;
            j++;
        }

    }

    @Override
    public Set<Position> getPreferredFields() {
        ArrayList<Position> fieldsWithNoPlants = worldMap.getFieldsWithNoPlants();
        PairOfLists lists = seperatePrefferedAndUnpreffred(fieldsWithNoPlants);
        return new HashSet<>(lists.firstList());
    }
}
