package util;

public class GridCellWidthCalculation {

    public static int determineGridCellWidth (int x) {
        if (x < 10) return 34;
        else if (x < 18) return 30;
        else if (x < 25) return 26;
        else if (x < 30) return 24;
        else return 22;
    }
}
