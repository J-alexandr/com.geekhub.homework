package task2;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archivator {
    private String parentDirectoryPath;

    public Archivator(String parentDirectoryPath) {
        this.parentDirectoryPath = parentDirectoryPath;
    }

    void archiveFiles(List<File> files, String archiveName) {
        String absoluteZipArchivePath = parentDirectoryPath.concat("\\").concat(archiveName).concat(".zip");

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(absoluteZipArchivePath))) {
            for (File file : files) {

                byte[] fileInBytes = readFileToBytes(file);
                if (fileInBytes.length != 0) {
                    ZipEntry entry1 = new ZipEntry(file.getCanonicalPath());
                    zout.putNextEntry(entry1);
                    zout.write(fileInBytes);
                    zout.closeEntry();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception got while zipping files. Message: " + e.getMessage());
        }
    }

    private byte[] readFileToBytes(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            return buffer;
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
