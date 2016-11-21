package task2;

import task2.source.FileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter parent directory absolute path:");
        String parentDirectoryPath = readParentDirectoryPath();

        FilesProvider filesProvider = new FilesProvider();
        if (filesProvider.isAllowed(parentDirectoryPath)) {
            List<File> files = filesProvider.getListOfFiles(parentDirectoryPath);
            Map<FileType, List<File>> filesByCategories = filesProvider.sortFilesByType(files);

            Archivator archivator = new Archivator();
            for (FileType type : FileType.values()) {
                try {
                    archivator.archiveFiles(filesByCategories.get(type), parentDirectoryPath, type);
                    System.out.println(type + " zipped successfully!");
                } catch (ArchivatorException e) {
                    System.out.println("Can't archivate " + type + ". Message: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Provided parent directory path is incorrect.");
        }
    }

    private static String readParentDirectoryPath() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}