package simulation;

import model.Position;

import java.util.Collection;
import java.util.Set;

public interface PlantGrowthManager {

    void growPlants (int numberOfPlantsToGrow, int nutritionalValue);

    Set<Position> getPreferredFields ();

}
