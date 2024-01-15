package presenter;
import model.Position;
import model.WorldMap;

public interface MapChangeListener {

    void mapChanged (WorldMap map, Position position);

}