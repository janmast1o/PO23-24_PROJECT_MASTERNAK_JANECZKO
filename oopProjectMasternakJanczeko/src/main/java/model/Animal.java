package model;
import java.util.List;

public class Animal {
    private final static int STARTENERGY = 150;         //as for now
    private final static int NUMBEROFGENES = 3;         //as for now
    private Position position;
    private final List<Integer> genes;
    private int activeGeneNumber;
    //AnimalHistory
    private int energyLeft;
    private WorldDirection facedSide;

    private AnimalInformation historian;

    public Animal(Position position, List<Integer> genes) {
        this.position = position;
        this.genes = genes;
        this.activeGeneNumber = 0;
        this.energyLeft = STARTENERGY;
        this.facedSide = WorldDirection.values()[(int) (Math.random() * WorldDirection.values().length)];
        historian = new AnimalInformation ();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getActiveGene() {
        return genes.get(activeGeneNumber);
    }

    public int getEnergyLeft() {
        return energyLeft;
    }

    public void changeEnergy(int changeEnergy) {
        this.energyLeft =  this.energyLeft + changeEnergy;
    }

    public WorldDirection getFacedSide() {
        return facedSide;
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }

    public void move() {
        facedSide = facedSide.performNextNTimes (getActiveGene());
        setPosition(getPosition().add(facedSide.toPosition()));         //as for now -> later check possibility to go to that place
        activeGeneNumber=(activeGeneNumber+1)%NUMBEROFGENES;
    }

    public void eat() {
        //checking for every plant whether there are animals there:
        //1 - easy case, 2 and more -> ConflictDealer
    }
}