package util;

import model.Position;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Position> {

    //private inner class
    private class Int {
        private int value;
        private Int (int value) {this.value = value;}
    }

    List<Position> positions;

    public Iterator<Position> iterator () {
        return positions.iterator();
    }

    public RandomPositionGenerator (int width, int height, int count) {
        positions = new LinkedList<>();
        generatePositionsRandomly (width,height,count);
    }

    private void generatePositionsRandomly (int x, int y, int c) {
        //Fisher Yates shuffle
        Random random = new Random ();

        Map<Int,Int> arraySimulation = new HashMap<>(0);
        Int selected;

        for (int i=0; i<c; i++) {
            selected = new Int (random.nextInt (i,x*y));
            if (arraySimulation.containsKey (selected)) addPosition (arraySimulation.get (selected),x);
            else addPosition (selected,x);
            fixArraySim (arraySimulation, new Int (i), selected);
        }
    }

    private void addPosition (Int n, int x) {
        positions.add (new Position (n.value%x,n.value/x));
    }

    private void fixArraySim (Map<Int,Int> arraySim, Int i, Int selected) {
        if (arraySim.containsKey (selected)) arraySim.remove (selected);

        if (arraySim.containsKey (i)) {
            arraySim.put (selected,arraySim.get(i));
            arraySim.remove (i);
        }
        else {
            arraySim.put (selected,i);
        }
    }

}