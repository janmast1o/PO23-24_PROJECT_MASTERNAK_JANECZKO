package model;
import java.util.ArrayList;

public class Animal {
    private Position position;
    private final ArrayList<Integer> genes;
    private int activeGeneNumber;
    private int energy;
    private WorldDirection facedSide;
    private AnimalInformation historian;

    public Animal (int birth, Position position, ArrayList<Integer> genes, int energy) {
        this.position = position;
        this.genes = genes;
        this.activeGeneNumber = 0;
        this.energy = energy;
        this.facedSide = WorldDirection.values()[(int) (Math.random() * WorldDirection.values().length)];
        this.historian = new AnimalInformation (birth);
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public Integer getActiveGene() {
        return genes.get(activeGeneNumber);
    }

    public ArrayList<Integer> getGenes () {
        return genes;
    }

    public void nextActiveGene () {
        activeGeneNumber = (activeGeneNumber+1)%(genes.size());
    }

    public int getEnergy() {
        return energy;
    }

    public void changeEnergy (Integer newEnergy) {
        this.energy =  energy + newEnergy;
    }

    public WorldDirection getFacedSide() {
        return facedSide;
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }

    public void moveAccordingToGene () {
        facedSide = facedSide.performNextNTimes (getActiveGene());
        setPosition(getPosition().add(facedSide.toPosition()));         //as for now -> later check possibility to go to that place
    }

    public int getNumberOfChildren () {
        return historian.getChildren().size();
    }

    public int getDateOfBirth () {
        return historian.getBirth();
    }

    public void turnAround () {
        facedSide = facedSide.performNextNTimes (4);
    }

    public int getDateOfDeath () {
        return historian.getDeath();
    }

    public void die (int death) {
        historian.die (death);
    }

    public Position newPotentialPosition () {
        return position.add (facedSide.toPosition());
    }
}