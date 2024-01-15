package simulation;

import model.Position;
import model.WorldMap;
import util.PairOfLists;

import java.util.ArrayList;
import java.util.List;

public class DefaultPlantGrowthManager extends PlantGrowthManager {

    public DefaultPlantGrowthManager (WorldMap worldMap) {
        super (worldMap);
    }

    @Override
    protected PairOfLists seperatePrefferedAndUnpreffred (List<Position> fieldsWithNoPlants, int equatorSpan) {
        ArrayList<Position> preffredFields = new ArrayList<>();
        ArrayList<Position> unpreffredFields = new ArrayList<>();

        for (Position position : fieldsWithNoPlants) {
            if (position.absYDifference (new Position (0,equatorSpan)) <= equatorSpan) {
                preffredFields.add(position);
            }
            else {
                unpreffredFields.add(position);
            }
        }

        return new PairOfLists (preffredFields,unpreffredFields);

    }
}
