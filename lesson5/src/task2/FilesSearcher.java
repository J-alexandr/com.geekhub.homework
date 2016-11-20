package task2;

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

class FilesSearcher {
    private String parentDirectoryPath;
    private Map<FileType, List<File>> filesByCategories;

    FilesSearcher(String parentDirectoryPath) {
        this.parentDirectoryPath = parentDirectoryPath;
        filesByCategories = new HashMap<>();
        filesByCategories.put(FileType.AUDIO, new ArrayList<>());
        filesByCategories.put(FileType.IMAGE, new ArrayList<>());
        filesByCategories.put(FileType.VIDEO, new ArrayList<>());
    }

    List<File> getListOfFiles() throws IOException {
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
}
