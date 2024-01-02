import model.WorldDirection;

public class Simulation {
    public static void main(String[] args) {
        System.out.println("Beginning");
        WorldDirection worldDirection = WorldDirection.EAST;
        for (int i = 0; i < 10; i++) {
            worldDirection=worldDirection.next();
        }
        System.out.println(worldDirection);
        worldDirection = WorldDirection.EAST;
        worldDirection = WorldDirection.performNextNTimes(worldDirection,10);
        System.out.println(worldDirection);
    }
}