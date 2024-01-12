package com.example.myjavafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

//Zwierzak od lewej gory do prawej i pozniej tak samo w 2 rzedzie
//Odpalanie asynchronicznie
//Mozliwosc stopowania bez terminacji okienka

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        VBox vbox = loader.load();
        Controller controller = loader.getController();
        controller.getNumber().setVisible(false);
        Button button = controller.getAcceptButton();

        button.setOnAction(e -> {
            int n = (Integer) controller.getNumberSpinner().getValue();
            controller.onButtonClick();
            GridPane grid = new GridPane();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    Rectangle rect = new Rectangle(60, 60);
                    rect.setStroke(javafx.scene.paint.Color.BLACK);
                    rect.setFill(javafx.scene.paint.Color.WHITE);
                    grid.add(rect, i, j);
                }
            }
            vbox.getChildren().add(grid);
        });

        Scene scene = new Scene(vbox, 1250, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}