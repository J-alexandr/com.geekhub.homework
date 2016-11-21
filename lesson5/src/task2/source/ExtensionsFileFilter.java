package task2.source;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ExtensionsFileFilter extends FileFilter {
    private Enum[] extensions;
    private String description;

    ExtensionsFileFilter(Enum[] extensions, String description) {
        this.extensions = extensions;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        for (Enum extension : extensions) {
            if (file.getName().toUpperCase().endsWith("." + extension.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
