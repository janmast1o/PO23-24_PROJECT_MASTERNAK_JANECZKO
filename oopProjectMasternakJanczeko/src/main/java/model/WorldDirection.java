package model;
import java.util.Random;

public enum WorldDirection {

    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public WorldDirection next () {
        return switch (this) {
            case NORTH -> NORTHEAST;
            case NORTHEAST -> EAST;
            case EAST -> SOUTHEAST;
            case SOUTHEAST -> SOUTH;
            case SOUTH -> SOUTHWEST;
            case SOUTHWEST -> WEST;
            case WEST -> NORTHWEST;
            case NORTHWEST -> NORTH;
        };
    }

    public WorldDirection performNextNTimes (int n) {
        WorldDirection direction = this;
        for (int i = 0; i < n; i++) {
            direction = direction.next ();
        }
        return direction;
    }

    public Position toVector () {
        return switch (this) {
            case NORTH -> new Position (0,1);
            case NORTHEAST -> new Position (1,1);
            case EAST -> new Position (1,0);
            case SOUTHEAST -> new Position (1,-1);
            case SOUTH -> new Position (0,-1);
            case SOUTHWEST -> new Position (-1,-1);
            case WEST -> new Position (-1,0);
            case NORTHWEST -> new Position (-1,1);
        };
    }

    public static WorldDirection randomDirection () {
        Random random = new Random();
        WorldDirection[] directions = values();
        return directions[random.nextInt(0, directions.length)];
    }

    public String toString () {
        return switch (this) {
            case NORTH -> "N";
            case NORTHEAST -> "NE";
            case EAST -> "E";
            case SOUTHEAST -> "SE";
            case SOUTH -> "S";
            case SOUTHWEST -> "SW";
            case WEST -> "W";
            case NORTHWEST -> "NW";
        };
    }

}