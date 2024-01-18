package simulation;

import model.Position;
import model.WorldMap;
import util.PairOfLists;

import java.util.ArrayList;
import java.util.List;

public class DefaultPlantGrowthManager extends AbstractPlantGrowthManager {

    public DefaultPlantGrowthManager (WorldMap worldMap) {
        super (worldMap);
    }

    @Override
    protected PairOfLists seperatePrefferedAndUnpreffred (List<Position> fieldsWithNoPlants) {
        ArrayList<Position> preferredFields = new ArrayList<>();
        ArrayList<Position> unpreferredFields = new ArrayList<>();

        for (Position position : fieldsWithNoPlants) {
            if (getWorldMap().withinEquatorSpan(position)) {
                preferredFields.add(position);
            }
            else {
                unpreferredFields.add(position);
            }
        }

        return new PairOfLists (preferredFields, unpreferredFields);

    }
}
