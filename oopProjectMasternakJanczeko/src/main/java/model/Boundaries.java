package model;
import java.util.ArrayList;
import java.util.Random;

public record Boundaries (Position lowerLeft, Position upperRight) {

    public boolean withinBoundariesX (Position position) {
        return lowerLeft.x() <= position.x() && upperRight.x() >= position.x();
    }

    public boolean withinBoundariesY (Position position) {
        return lowerLeft.y() <= position.y() && upperRight.y() >= position.y();
    }

    public Position transcendVerticalBorder (Position position) {
        if (position.x() < lowerLeft.x()) {
            return new Position (upperRight.x(), position.y());
        }
        else {
            return new Position(lowerLeft.x(), position.y());
        }
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

    public Position getRandomPositionWithinBoundaries () {
        Random random = new Random();
        int a = random.nextInt(lowerLeft().x(), upperRight().x()+1);
        int b = random.nextInt(lowerLeft().y(), upperRight().y()+1);
        return new Position (a,b);
    }

}