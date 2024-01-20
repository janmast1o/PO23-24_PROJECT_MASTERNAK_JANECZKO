package presenter;

import javafx.application.Platform;

public class UIMapListenerAndObserver implements MapChangeListener, SimulationObserver {

    private SimulationPresenter presenter;

    public UIMapListenerAndObserver (SimulationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public synchronized void mapChanged () {
        Platform.runLater(()-> {
            try {
                presenter.updateMapGrid();
            }
            catch (InterruptedException exception) {
                System.exit(1);
            }
        });
    }

    @Override
    public synchronized void simulationChanged (int numberOfAnimals, int numberOfPlants, int mostPopularGene, double averageEnergy, double averageLifetime, double averageNumberOfChildren, int day, int numberOfUnoccupiedFields) {
        Platform.runLater(() -> {
            try {
                presenter.changeStatistics(
                    numberOfAnimals, numberOfPlants, mostPopularGene, averageEnergy, averageLifetime, averageNumberOfChildren, day, numberOfUnoccupiedFields
                );
            }
            catch (InterruptedException exception) {
                System.exit(1);
            }
        });
    }


}
