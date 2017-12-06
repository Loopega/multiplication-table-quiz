package pl.devwannabe;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by root on 22.11.17.
 */
public class SessionFile {
    private String filePath;
    public SessionFile(String filePath) {
        this.filePath = filePath;
    }
    public void append(List<String> textLines) {
        Path file = Paths.get(filePath);
        try {
            Files.write(file, textLines, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error during writing file");
        }
    }
}
