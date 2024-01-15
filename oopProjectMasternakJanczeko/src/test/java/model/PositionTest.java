package model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void addPostition() {
        //given
        Random random = new Random();

        //when
        boolean allCorrect = true;
        for (int i=0; i<50; i++) {
            int a = random.nextInt();
            int b = random.nextInt();
            int c = random.nextInt();
            int d = random.nextInt();

            Position firstPosition = new Position (a,b);
            Position secondPosition = new Position (c,d);
            Position resultPosition = firstPosition.addPosition(secondPosition);

            allCorrect = allCorrect && resultPosition.x() == a+c && resultPosition.y() == b+d;

        }

        //then
        assertTrue (allCorrect);

    }

    @Test
    void absXDifference() {
        //given
        Random random = new Random();

        //when
        boolean allCorrect = true;
        for (int i=0; i<50; i++) {
            int a = random.nextInt();
            int b = random.nextInt();
            int c = random.nextInt();
            int d = random.nextInt();

            Position firstPosition = new Position (a,b);
            Position secondPosition = new Position (c,d);

            allCorrect = allCorrect && firstPosition.absXDifference(secondPosition) == Math.abs(a-c);

        }

        //then
        assertTrue (allCorrect);
    }

    @Test
    void absYDifference() {
        //given
        Random random = new Random();

        //when
        boolean allCorrect = true;
        for (int i=0; i<50; i++) {
            int a = random.nextInt();
            int b = random.nextInt();
            int c = random.nextInt();
            int d = random.nextInt();

            Position firstPosition = new Position (a,b);
            Position secondPosition = new Position (c,d);

            allCorrect = allCorrect && firstPosition.absYDifference(secondPosition) == Math.abs(b-d);

        }

        //then
        assertTrue (allCorrect);
    }

    @Test
    void precedes() {
        //given
        Random random = new Random();

        //when
        boolean allCorrect = true;
        for (int i=0; i<50; i++) {
            int a = random.nextInt(0,100);
            int b = random.nextInt(0,100);
            int c = random.nextInt(a,100);
            int d = random.nextInt(b,100);

            Position firstPosition = new Position (a,b);
            Position secondPosition = new Position (c,d);

            allCorrect = allCorrect && firstPosition.precedes(secondPosition);
            allCorrect = allCorrect && firstPosition.precedes(firstPosition); //self
        }

        //then
        assertTrue (allCorrect);
    }

    @Test
    void follows() {
        //given
        Random random = new Random();

        //when
        boolean allCorrect = true;
        for (int i=0; i<50; i++) {
            int a = random.nextInt(0,100);
            int b = random.nextInt(0,100);
            int c = random.nextInt(a,100);
            int d = random.nextInt(b,100);

            Position firstPosition = new Position (a,b);
            Position secondPosition = new Position (c,d);

            allCorrect = allCorrect && secondPosition.follows(firstPosition);
            allCorrect = allCorrect && secondPosition.follows(secondPosition); //self
        }

        //then
        assertTrue (allCorrect);
    }
}