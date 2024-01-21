package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SimulationFileReader {

    public static synchronized ArrayList<String> readFile (String directoryName, String fileName) {
        try {
            ArrayList<String> readLines = new ArrayList<>();
            String resourcesDirectory = "src/main/resources";
            Path directoryPath = Paths.get(resourcesDirectory,directoryName);

            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = Paths.get(directoryPath.toString(), fileName);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    readLines.add(line);
                }
            }

            return readLines;

        }
        catch (IOException exception) {
            return null;
        }
    }

}
