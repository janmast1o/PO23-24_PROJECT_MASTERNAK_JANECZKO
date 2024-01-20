package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileWriter {

    public static void saveToFile (String[] lines, String fileName) {
        String resourcesDirectory = "src/main/resources/saved_configurations";

        Path filePath = Paths.get(resourcesDirectory, fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            Files.write(filePath, Arrays.stream(lines).toList());
        }
        catch (IOException exception) {;}
    }

}
