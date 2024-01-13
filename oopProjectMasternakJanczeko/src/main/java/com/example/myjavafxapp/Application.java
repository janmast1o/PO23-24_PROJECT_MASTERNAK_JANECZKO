package com.example.myjavafxapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        VBox vbox = loader.load();
        Controller controller = loader.getController();
        controller.getNumber().setVisible(false);
        Button button = controller.getAcceptButton();

        button.setOnAction(e -> {
            int rows = 2;
            int n = controller.getNumberSpinner().getValue();
            controller.onButtonClick();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < rows; j++) {
                    Rectangle rect = new Rectangle(60, 60);
                    rect.setStroke(javafx.scene.paint.Color.BLACK);
                    rect.setFill(javafx.scene.paint.Color.WHITE);
                    grid.add(rect, i, j);
                }
            }
            vbox.getChildren().add(grid);

            Button stopButton = new Button("Stop Animation");
            vbox.getChildren().add(stopButton);
            Button resumeButton = new Button("Resume Animation");
            vbox.getChildren().add(resumeButton);

            animateAnimal(grid, n, rows, stopButton, resumeButton);
        });
        Scene scene = new Scene(vbox, 1250, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void animateAnimal(GridPane grid, int gridColumnSize, int gridRowSize, Button stopButton, Button resumeButton) {
        //Animal animal = new Animal(0, new Position(0, 0), new ArrayList<>(Arrays.asList(1, 2, 3, 4)), 7);
        Rectangle animal = new Rectangle(60, 60);
        animal.setFill(Color.AQUAMARINE);
        grid.add(animal, 0, 0);
        //Dzieki timelinowi ta animacja jest wykonywana asynchronicznie (tworzony jest nowy osobny watek)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> moveAnimal(animal, grid, gridColumnSize, gridRowSize)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        stopButton.setOnAction(e -> timeline.pause());
        resumeButton.setOnAction(e -> timeline.play());
    }

    private void moveAnimal(Rectangle animal, GridPane grid, int gridColumnSize, int gridRowSize) {
        int currentColumn = GridPane.getColumnIndex(animal);
        int currentRow = GridPane.getRowIndex(animal);

        grid.getChildren().remove(animal);
        if (currentColumn == gridColumnSize - 1 && currentRow == gridRowSize - 1) {
            grid.add(animal, 0, 0);
        }
        else if (currentColumn == gridColumnSize - 1) {
            grid.add(animal, 0, currentRow + 1);
        }
        else {
            grid.add(animal, currentColumn + 1, currentRow);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}