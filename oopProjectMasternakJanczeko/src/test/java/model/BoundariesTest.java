package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoundariesTest {

    @Test
    void withinBoundaries() {
        //given
        Boundaries boundaries = new Boundaries (new Position (0,0), new Position (70,40));

        //when
        boolean shouldBeWithin1 = boundaries.withinBoundaries(new Position (1,10));
        boolean shouldBeWithin2 = boundaries.withinBoundaries(new Position (0,10));
        boolean shouldBeWithin3 = boundaries.withinBoundaries(new Position (56,0));
        boolean shouldBeWithin4 = boundaries.withinBoundaries(new Position (70,40));
        boolean shouldNotBeWithin = boundaries.withinBoundaries(new Position (71,41));

        //
        assertTrue (
                shouldBeWithin1 &&
                shouldBeWithin2 &&
                shouldBeWithin3 &&
                shouldBeWithin4 &&
                !shouldNotBeWithin
        );

    }

    @Test
    void size() {
        //given
        Boundaries boundaries = new Boundaries (new Position (1,1), new Position (7,7));

        //when
        int calculatedSize = boundaries.size();

        //then
        assertTrue (calculatedSize == 49);
    }

    @Test
    void getAllFieldsWithinBoundaries() {
        //given
        Boundaries boundaries = new Boundaries (new Position(1,6), new Position(2,7));

        //when
        List<Position> positions = List.of (new Position (1,6), new Position (2,6), new Position (1,7), new Position (2,7));
        boolean allCorrect = true;
        for (Position position : boundaries.getAllFieldsWithinBoundaries()) {
            allCorrect = allCorrect && positions.contains(position);
        }

        //then
        assertTrue (allCorrect);

    }
}