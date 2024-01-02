package model;

public enum WorldDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;
    public WorldDirection next() {
        return switch(this) {
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
    public static WorldDirection performNextNTimes(WorldDirection direction, int n) {
        for(int i=0;i<n;i++) {
            direction = direction.next();
        }
        return direction;
    }
    public Position toPosition() {
        return switch(this) {
            case NORTH -> new Position(0,1);
            case NORTHEAST -> new Position(1,1);
            case EAST -> new Position(1,0);
            case SOUTHEAST -> new Position(1,-1);
            case SOUTH -> new Position(0,-1);
            case SOUTHWEST -> new Position(-1,-1);
            case WEST -> new Position(-1,0);
            case NORTHWEST -> new Position(-1,1);
        };
    }
}