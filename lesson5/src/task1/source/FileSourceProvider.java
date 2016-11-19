package task1.source;

import task1.exceptions.SourceLoadingException;

import java.io.*;

/**
 * Implementation for loading content from local file system.
 * This implementation supports absolute paths to local file system without specifying file:// protocol.
 * Examples c:/1.txt or d:/pathToFile/file.txt
 */
public class FileSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) {
        return new File(pathToSource).isFile();
    }

    @Override
    public String load(String pathToSource) throws SourceLoadingException {
        try (FileInputStream inputStream = new FileInputStream(pathToSource)) {
            return SourceUtils.toString(inputStream);
        } catch (IOException e) {
            throw new SourceLoadingException(e);
        }
    }
}
