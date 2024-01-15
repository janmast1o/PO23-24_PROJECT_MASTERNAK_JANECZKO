package model;

public record Date (int date) implements Comparable<Date> {

    public int compareTo (Date other) {
        return other.date - this.date;
    }

}