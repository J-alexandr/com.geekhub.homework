package task2;

/*
 Zip File Maker

 The user enters a path to a directory and the program zips up the directory items into several zip files.
 The program should zip up files of the given directory and all the sub-directories.
 The program supports such types:
 - audio (mp3, wav, wm);
 - video (avi, mp4, flv);
 - image (jpeg, jpg, gif, tiff, png);
 As the result you must have 3 archives: audio.zip, video.zip and image.zip.
 You can use File, FileFilter, (G)ZIPOutputStream or something else.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<FileType, List<File>> filesByCategories = new HashMap<>();
    static String parentDirectoryPath;

    public static void main(String[] args) {
        List<File> files = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter parent directory absolute path:");
            parentDirectoryPath = reader.readLine();
            FilesSearcher filesSearcher = new FilesSearcher(parentDirectoryPath);
            files = filesSearcher.getListOfFiles();
        } catch (IOException e) {
            System.out.println("Exception got while reading input steam. Message: " + e.getMessage());
            System.exit(-1);
        }

        filesByCategories.put(FileType.AUDIO, new ArrayList<>());
        filesByCategories.put(FileType.IMAGE, new ArrayList<>());
        filesByCategories.put(FileType.VIDEO, new ArrayList<>());

        sortFilesByType(files);

        Archivator archivator = new Archivator(parentDirectoryPath);
        archivator.archiveFiles(filesByCategories.get(FileType.AUDIO));
    }

    static void sortFilesByType(List<File> files) {
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
    }
}