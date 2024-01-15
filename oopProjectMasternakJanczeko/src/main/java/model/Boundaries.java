package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Boundaries (Position lowerLeft, Position upperRight) {

    public boolean withinBoundaries (Position position) {
        return lowerLeft.precedes (position) && upperRight.follows (position);
    }

    public int size () {
        return (lowerLeft.absXDifference(upperRight)+1) * (lowerLeft.absYDifference(upperRight)+1);
    }

    public ArrayList<Position> getAllFieldsWithinBoundaries () {
        ArrayList<Position> positionList = new ArrayList<>();
        for (int i = lowerLeft.x(); i <= upperRight().x(); i++) {
            for (int j = lowerLeft.y(); j <= upperRight().y(); j++) {
                positionList.add(new Position(i,j));
            }
        }
        return positionList;
    }

}
