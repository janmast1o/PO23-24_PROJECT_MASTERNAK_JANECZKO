package simulation;

import model.Position;
import model.WorldMap;
import util.PairOfLists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AlternativePlantGrowthManager extends AbstractPlantGrowthManager {

    public AlternativePlantGrowthManager (WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    protected PairOfLists seperatePrefferedAndUnpreffred(List<Position> fieldsWithNoPlants) {
        Set<Position> recentDeathPlaces = getWorldMap().getRecentDeathPlaces();
        ArrayList<Position> preferredFields = new ArrayList<>();
        ArrayList<Position> unpreferredFields = new ArrayList<>();

        for (Position position : fieldsWithNoPlants) {
            if (getWorldMap().withinEquatorSpan(position) || recentDeathPlaces.contains(position)) {
                preferredFields.add(position);
            }
            else {
                unpreferredFields.add(position);
            }
        }

        return new PairOfLists (preferredFields, unpreferredFields);
    }
}
