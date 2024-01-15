package presenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SimulationApp extends Application {

    public void start (Stage primaryStage) throws IOException {
        primaryStage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        UIMapListener uiMapListener = loader.getController();
        configureStage(primaryStage,viewRoot);
    }

    private void configureStage (Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        //!!!
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("simulation.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

}