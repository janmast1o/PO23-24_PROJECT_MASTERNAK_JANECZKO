package util;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FisherYatesShuffle {

    public static List<Integer> shuffle (int count, int n) {
        List<Integer> chosenNumbers = new LinkedList<>();
        Random randomIndexChoice = new Random();
        HashMap<Integer,Integer> arraySim = new HashMap<>();

        for (int i=0; i<count; i++) {
            Integer selectedIndex = randomIndexChoice.nextInt(i,n);
            if (arraySim.containsKey(selectedIndex)) {
                chosenNumbers.add(arraySim.get(selectedIndex));
                fixArraySim(arraySim,i,selectedIndex);
            }
            else {
                chosenNumbers.add(selectedIndex);
            }
        }

        return chosenNumbers;

    }

    private static void fixArraySim (HashMap<Integer,Integer> arraySim, Integer i, Integer selected) {
        arraySim.remove(selected);
        if (arraySim.containsKey(i)) {
             arraySim.put(selected,arraySim.get(i));
             arraySim.remove(i);
        }
        else {
            arraySim.put(selected,i);
        }

    }

}