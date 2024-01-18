package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoundariesTest {

    @Test
    void withinBoundariesX() {
        //given
        Boundaries boundaries = new Boundaries (new Position (0,0), new Position (70,40));

        //when
        boolean shouldBeWithin1 = boundaries.withinBoundariesX(new Position (1,10));
        boolean shouldBeWithin2 = boundaries.withinBoundariesX(new Position (0,10));
        boolean shouldBeWithin3 = boundaries.withinBoundariesX(new Position (56,0));
        boolean shouldBeWithin4 = boundaries.withinBoundariesX(new Position (70,40));
        boolean shouldNotBeWithin1 = boundaries.withinBoundariesX(new Position (71,41));
        boolean shouldNotBeWithin2 = boundaries.withinBoundariesX(new Position (71,10));

        //
        assertTrue (
                shouldBeWithin1 &&
                shouldBeWithin2 &&
                shouldBeWithin3 &&
                shouldBeWithin4 &&
                !shouldNotBeWithin1 &&
                !shouldNotBeWithin2
        );

    }

    @Test
    void withinBoundariesY () {
        //given
        Boundaries boundaries = new Boundaries (new Position (0,0), new Position (70,40));

        //when
        boolean shouldBeWithin1 = boundaries.withinBoundariesY(new Position (1,10));
        boolean shouldBeWithin2 = boundaries.withinBoundariesY(new Position (0,10));
        boolean shouldBeWithin3 = boundaries.withinBoundariesY(new Position (56,0));
        boolean shouldBeWithin4 = boundaries.withinBoundariesY(new Position (70,40));
        boolean shouldNotBeWithin1 = boundaries.withinBoundariesY(new Position (71,41));
        boolean shouldNotBeWithin2 = boundaries.withinBoundariesY(new Position (7,41));

        //then
        assertTrue (
                shouldBeWithin1 &&
                        shouldBeWithin2 &&
                        shouldBeWithin3 &&
                        shouldBeWithin4 &&
                        !shouldNotBeWithin1 &&
                        !shouldNotBeWithin2
        );

    }

    @Test
    void transcendLeftVerticalBorder () {
        //given
        Boundaries boundaries = new Boundaries(new Position (1,1), new Position(70,40));
        Position beyondLeftBorer = new Position(0,30);

        //when
        Position transcended = boundaries.transcendVerticalBorder(beyondLeftBorer);

        //then
        assertTrue (transcended.equals(new Position (70,30)));

    }

    @Test
    void transcendRightVerticalBorder () {
        //given
        Boundaries boundaries = new Boundaries(new Position (1,1), new Position(70,40));
        Position beyondRightBorer = new Position(71,30);

        //when
        Position transcended = boundaries.transcendVerticalBorder(beyondRightBorer);

        //then
        assertTrue (transcended.equals(new Position (1,30)));
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

    @Test
    void getHeight () {
        //given
        Boundaries boundaries = new Boundaries(new Position(0,0), new Position(10,16));

        //when & then
        assertTrue (boundaries.getHeight() == 17);
    }

    @Test
    void withinEquatorSpan () {
        //given
        Boundaries boundaries = new Boundaries(new Position(0,0), new Position(10,16));

        //when
        boolean shouldBeWithin1 = boundaries.withinEquatorSpan(new Position(3,7));
        boolean shouldBeWithin2 = boundaries.withinEquatorSpan(new Position(8,9));
        boolean shouldBeWithin3 = boundaries.withinEquatorSpan(new Position(1,8));
        boolean shouldBeWithin4 = boundaries.withinEquatorSpan(new Position(7,7));
        boolean shouldBeWithin5 = boundaries.withinEquatorSpan(new Position(0,9));
        boolean shouldBeWithin6 = boundaries.withinEquatorSpan(new Position(8,8));
        boolean shouldNotBeWithin1 = boundaries.withinEquatorSpan(new Position(1,10));
        boolean shouldNotBeWithin2 = boundaries.withinEquatorSpan(new Position(4,6));

        //then
        assertTrue(
                shouldBeWithin1 &&
                shouldBeWithin2 &&
                shouldBeWithin3 &&
                shouldBeWithin4 &&
                shouldBeWithin5 &&
                shouldBeWithin6 &&
                !shouldNotBeWithin1 &&
                !shouldNotBeWithin2
        );
    }
}