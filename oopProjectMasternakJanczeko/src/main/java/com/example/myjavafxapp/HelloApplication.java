package com.example.myjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Rysowanie grida w fx
//Zwierzak od lewej gory do prawej i pozniej tak samo w 2 rzedzie
//Wprowadzenie n jako dlugosci mapy
//Odpalanie asynchronicznie
//Mozliwosc stopowania bez terminacji okienka

public class HelloApplication extends Application {
    /*@FXML
    public void startAnimation() throws Exception{
        GridPane grid = new GridPane();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label("Komórka (" + i + ", " + j + ")");
                grid.add(label, i, j);
            }
        }

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //Animal animal = loader.getController();

        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Animal movement scheme");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}