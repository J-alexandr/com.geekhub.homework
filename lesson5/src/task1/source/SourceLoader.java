package task1.source;

import task1.exceptions.SourceLoadingException;

import java.util.ArrayList;
import java.util.List;

/**
 * SourceLoader should contains all implementations of SourceProviders to be able to load different sources.
 */
public class SourceLoader {
    private List<SourceProvider> sourceProviders = new ArrayList<>();

    public SourceLoader() {
        sourceProviders.add(new URLSourceProvider());
        sourceProviders.add(new FileSourceProvider());
    }

    public String loadSource(String pathToSource) throws SourceLoadingException {
        for (SourceProvider sourceProvider : sourceProviders) {
            if (sourceProvider.isAllowed(pathToSource)) {
                return sourceProvider.load(pathToSource);
            }
        }

        return null;
    }
}