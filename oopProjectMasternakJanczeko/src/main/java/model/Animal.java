package model;
import java.util.ArrayList;

public class Animal {
    private final static int STARTENERGY = 150;         //as for now
    private final static int NUMBEROFGENES = 3;         //as for now
    private Position position;
    private final ArrayList<Integer> genes;
    private int activeGeneNumber;
    private int energyLeft;
    private WorldDirection facedSide;
    private AnimalInformation historian;

    public Animal(Position position, ArrayList<Integer> genes) {
        this.position = position;
        this.genes = genes;
        this.activeGeneNumber = 0;
        this.energyLeft = STARTENERGY;
        this.facedSide = WorldDirection.values()[(int) (Math.random() * WorldDirection.values().length)];
        this.historian = new AnimalInformation (0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
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
        setPosition(getPosition().add(facedSide.toPosition()));
        //as for now -> later check chance to go to that place
        //right and left edges are possible to cross; upper and bottom edges change facedSite to opposite
        activeGeneNumber=(activeGeneNumber+1)%NUMBEROFGENES;
    }

    public void eat() {
        //checking for every plant whether there are animals there
        //if eat then -> historian.increaseNumberOfEatenPlants();
    }
}