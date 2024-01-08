package model;

public record Boundaries (Position lowerLeft, Position upperRight) {

    public boolean withinBoundaries (Position position) {
        return lowerLeft.precedes (position) && upperRight.follows (position);
    }

}
