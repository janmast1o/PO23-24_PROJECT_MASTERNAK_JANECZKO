package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldDirectionTest {

    @Test
    void toVectorNorth () {
        //given
        WorldDirection north = WorldDirection.NORTH;

        //when
        boolean isNorthVectorProper = north.toVector().equals(new Position (0,1));

        //then
        assertTrue (isNorthVectorProper);
    }

    @Test
    void toVectorNorthEast () {
        //given
        WorldDirection northeast = WorldDirection.NORTHEAST;

        //when
        boolean isNortheastVectorProper = northeast.toVector().equals(new Position (1,1));

        //then
        assertTrue (isNortheastVectorProper);
    }

    @Test
    void toVectorEast () {
        //given
        WorldDirection east = WorldDirection.EAST;

        //when
        boolean isEastVectorProper = east.toVector().equals(new Position (1,0));

        //then
        assertTrue (isEastVectorProper);
    }

    @Test
    void toVectorSouthEast () {
        //given
        WorldDirection southeast = WorldDirection.SOUTHEAST;

        //when
        boolean isSoutheastVectorProper = southeast.toVector().equals(new Position (1,-1));

        //then
        assertTrue (isSoutheastVectorProper);
    }

    @Test
    void toVectorSouth () {
        //given
        WorldDirection south = WorldDirection.SOUTH;

        //when
        boolean isSouthVectorProper = south.toVector().equals(new Position (0,-1));

        //then
        assertTrue (isSouthVectorProper);
    }

    @Test
    void toVectorSouthWest () {
        //given
        WorldDirection southwest = WorldDirection.SOUTHWEST;

        //when
        boolean isSouthwestVectorProper = southwest.toVector().equals(new Position (-1,-1));

        //then
        assertTrue (isSouthwestVectorProper);
    }

    @Test
    void toVectorWest () {
        //given
        WorldDirection west = WorldDirection.WEST;

        //when
        boolean isWestVectorProper = west.toVector().equals(new Position (-1,0));

        //then
        assertTrue (isWestVectorProper);
    }

    @Test
    void toVectorNorthwest () {
        //given
        WorldDirection northwest = WorldDirection.NORTHWEST;

        //when
        boolean isNorthwestVectorProper = northwest.toVector().equals(new Position (-1,1));

        //then
        assertTrue (isNorthwestVectorProper);
    }

    @Test
    void testEastToSoutheast() {
        assertTrue(WorldDirection.EAST.next().equals(WorldDirection.SOUTHEAST));
    }

    @Test
    void testSoutheastToSouth() {
        assertTrue(WorldDirection.SOUTHEAST.next().equals(WorldDirection.SOUTH));
    }

    @Test
    void testSouthToSouthwest() {
        assertTrue(WorldDirection.SOUTH.next().equals(WorldDirection.SOUTHWEST));
    }

    @Test
    void testSouthwestToWest() {
        assertTrue(WorldDirection.SOUTHWEST.next().equals(WorldDirection.WEST));
    }

    @Test
    void testWestToNorthwest() {
        assertTrue(WorldDirection.WEST.next().equals(WorldDirection.NORTHWEST));
    }

    @Test
    void testNorthwestToNorth() {
        assertTrue(WorldDirection.NORTHWEST.next().equals(WorldDirection.NORTH));
    }

    @Test
    void performNextNTimesNorth () {
        //given
        WorldDirection north = WorldDirection.NORTH;
        List<WorldDirection> followingDirections = List.of (
                WorldDirection.NORTH,
                WorldDirection.NORTHEAST,
                WorldDirection.EAST,
                WorldDirection.SOUTHEAST,
                WorldDirection.SOUTH,
                WorldDirection.SOUTHWEST,
                WorldDirection.WEST,
                WorldDirection.NORTHWEST,
                WorldDirection.NORTH
                );

        //when
        boolean allCorrect = true;
        for (int i=0; i< followingDirections.size(); i++) {
            allCorrect = allCorrect && north.performNextNTimes(i).equals(followingDirections.get(i));
        }

        //then
        assertTrue (allCorrect);
    }

    @Test
    void performNextNTimesNortheast() {
        //given
        WorldDirection northeast = WorldDirection.NORTHEAST;
        List<WorldDirection> followingDirections = List.of(
                WorldDirection.NORTHEAST,
                WorldDirection.EAST,
                WorldDirection.SOUTHEAST,
                WorldDirection.SOUTH,
                WorldDirection.SOUTHWEST,
                WorldDirection.WEST,
                WorldDirection.NORTHWEST,
                WorldDirection.NORTH,
                WorldDirection.NORTHEAST
        );

        //when
        boolean allCorrect = true;
        for (int i = 0; i < followingDirections.size(); i++) {
            allCorrect = allCorrect && northeast.performNextNTimes(i).equals(followingDirections.get(i));
        }

        //then
        assertTrue(allCorrect);
    }

}