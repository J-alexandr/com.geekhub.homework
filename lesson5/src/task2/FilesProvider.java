package task2;

import task2.source.FileFiltersManager;
import task2.source.FileType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class FilesProvider {
    private Map<FileType, List<File>> filesByCategories;

    FilesProvider() {
        filesByCategories = new HashMap<>();
        for (FileType type : FileType.values()) {
            filesByCategories.put(type, new ArrayList<>());
        }
    }

    List<File> getListOfFiles(String parentDirectoryPath) throws IOException {
        return Files.walk(Paths.get(parentDirectoryPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    Map<FileType, List<File>> sortFilesByType(List<File> files) {
        FileFiltersManager fileFiltersManager = new FileFiltersManager();

        for (File file : files) {
            if (fileFiltersManager.getAudioFileFilter().accept(file)) {
                filesByCategories.get(FileType.AUDIO).add(file);
            } else if (fileFiltersManager.getImageFileFilter().accept(file)) {
                filesByCategories.get(FileType.IMAGE).add(file);
            } else if (fileFiltersManager.getVideoFileFilter().accept(file)) {
                filesByCategories.get(FileType.VIDEO).add(file);
            }
        }

        return filesByCategories;
    }

    boolean isAllowed(String pathToSource) {
        return new File(pathToSource).isDirectory();
    }
}
