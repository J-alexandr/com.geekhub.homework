package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String parentDirectoryPath = readParentDirectoryPath();

        FilesSearcher filesSearcher = new FilesSearcher(parentDirectoryPath);
        List<File> files = filesSearcher.getListOfFiles();
        Map<FileType, List<File>> filesByCategories = filesSearcher.sortFilesByType(files);

        Archivator archivator = new Archivator(parentDirectoryPath);

        archivator.archiveFiles(filesByCategories.get(FileType.AUDIO), FileType.AUDIO.toString());
        archivator.archiveFiles(filesByCategories.get(FileType.IMAGE), FileType.IMAGE.toString());
        archivator.archiveFiles(filesByCategories.get(FileType.VIDEO), FileType.VIDEO.toString());
    }

    private static String readParentDirectoryPath() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter parent directory absolute path:");
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Exception got while reading path. Message: " + e.getMessage());
            return null;
        }
    }
}