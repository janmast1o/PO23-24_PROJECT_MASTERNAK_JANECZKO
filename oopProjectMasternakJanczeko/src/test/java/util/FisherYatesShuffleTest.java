package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FisherYatesShuffleTest {

    @Test
    void showResultOfRunningFisherYatesShuffle () {
        System.out.println(FisherYatesShuffle.shuffle(12,12));
        assertTrue(true);
    }
}