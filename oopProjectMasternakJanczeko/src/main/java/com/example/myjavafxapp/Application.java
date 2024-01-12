package com.example.myjavafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
//Rysowanie grida w fx
//Zwierzak od lewej gory do prawej i pozniej tak samo w 2 rzedzie
//Odpalanie asynchronicznie
//Mozliwosc stopowania bez terminacji okienka

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox vbox = FXMLLoader.load(getClass().getResource("view.fxml"));
        Button button = new Button("Create Grid");
        vbox.getChildren().add(button);
        button.setOnAction(e -> {
            GridPane grid = new GridPane();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Rectangle rect = new Rectangle(40, 40);
                    rect.setStroke(javafx.scene.paint.Color.BLACK); // obramowanie
                    rect.setFill(javafx.scene.paint.Color.WHITE); // wypełnienie
                    grid.add(rect, i, j);
                }
            }
            vbox.getChildren().add(grid);
        });

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}