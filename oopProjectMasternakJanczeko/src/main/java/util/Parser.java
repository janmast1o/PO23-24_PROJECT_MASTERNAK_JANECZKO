package util;

public class Parser {

    public static int parse (String[] inputs) throws IllegalArgumentException {
        if (inputs.length != 1) {
            throw new IllegalArgumentException ();
        }
        else {
            return Integer.parseInt(inputs[0]);
        }
    }

}