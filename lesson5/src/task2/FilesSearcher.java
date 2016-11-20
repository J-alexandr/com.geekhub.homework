package task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FilesSearcher {
    private String parentDirectoryPath;

    public FilesSearcher(String parentDirectoryPath) {
        this.parentDirectoryPath = parentDirectoryPath;
    }

    List<File> getListOfFiles() throws IOException {
        return Files.walk(Paths.get(parentDirectoryPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
