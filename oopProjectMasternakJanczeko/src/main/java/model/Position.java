package model;

public record Position (int x, int y) {

    public Position add (Position other){
        return new Position(this.x+other.x,this.y+other.y);
    }

    public boolean precedes (Position otherPosition) {
        return this.x <= otherPosition.x && this.y <= otherPosition.y;
    }

    public boolean follows (Position otherPosition) {
        return this.x >= otherPosition.x && this.y >= otherPosition.y;
    }

}
