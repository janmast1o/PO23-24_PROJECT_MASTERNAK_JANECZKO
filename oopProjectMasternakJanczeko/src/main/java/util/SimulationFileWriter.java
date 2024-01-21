package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimulationFileWriter {

    public static synchronized void saveToFile (String directoryName, String fileName, String[] contents, boolean append) {
        try {
            String resourcesDirectory = "src/main/resources";
            Path directoryPath = Paths.get(resourcesDirectory, directoryName);

            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Path filePath = Paths.get(directoryPath.toString(), fileName);

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), append));
            for (String line : contents) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        }
        catch (IOException exception) {;}
    }

}
