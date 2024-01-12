package com.example.myjavafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class Controller {
    @FXML
    private Spinner<Integer> numberSpinner;

    @FXML
    private Label number;

    @FXML
    private Label enterText;

    @FXML
    private Button acceptButton;

    public Spinner<Integer> getNumberSpinner() {
        return numberSpinner;
    }

    public Label getNumber() {
        return number;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    @FXML
    public void onButtonClick() {
        number.setVisible(true);
        number.setText(Integer.toString((Integer) numberSpinner.getValue()));
        enterText.setVisible(false);
        numberSpinner.setVisible(false);
        acceptButton.setVisible(false);
        //Animal animal = new Animal(0,new Position(0,0), new ArrayList<>(Arrays.asList(1,2,3,4)),7);
        //WorldMap worldMap = new WorldMap(new Boundaries(new Position(0,0),new Position(n,1)));
    }
}