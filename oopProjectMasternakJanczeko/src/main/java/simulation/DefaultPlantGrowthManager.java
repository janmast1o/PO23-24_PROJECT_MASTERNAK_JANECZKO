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
    protected PairOfLists seperatePrefferedAndUnpreffered (List<Position> fieldsWithNoPlants, int equatorSpan) {
        ArrayList<Position> prefferedFields = new ArrayList<>();
        ArrayList<Position> unprefferedFields = new ArrayList<>();

        for (Position position : fieldsWithNoPlants) {
            if (position.absYDifference (new Position (0,equatorSpan)) <= equatorSpan) {
                prefferedFields.add(position);
            }
            else {
                unprefferedFields.add(position);
            }
        }

        return new PairOfLists (prefferedFields,unprefferedFields);
    }
}