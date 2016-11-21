package task2;

import task2.source.FileType;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Archivator {

    void archiveFiles(List<File> files, String parentDirectoryPath, FileType type) throws ArchivatorException {
        String absoluteZipArchivePath = parentDirectoryPath.concat("\\")
                .concat(type.toString())
                .concat(".zip");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(absoluteZipArchivePath))) {
            for (File file : files) {
                byte[] fileInBytes = readFileToBytes(file);

                ZipEntry entry = new ZipEntry(getRelativeFilePath(file, parentDirectoryPath));
                zipOutputStream.putNextEntry(entry);
                zipOutputStream.write(fileInBytes);
                zipOutputStream.closeEntry();
            }
        } catch (Exception e) {
            throw new ArchivatorException(e);
        }
    }

    private byte[] readFileToBytes(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            return buffer;
        }
    }

    private String getRelativeFilePath(File file, String parentDirectoryPath) {
        String relativePath = file.getAbsolutePath()
                .replace(parentDirectoryPath.replaceAll("/","\\\\"), "");

        if (relativePath.startsWith("\\"))
            return relativePath.replaceFirst("\\\\", "");

        return relativePath;
    }
}
