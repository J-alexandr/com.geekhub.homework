package task1.source;

import java.io.IOException;
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

    public String loadSource(String pathToSource) throws IOException {
        SourceProvider provider = null;
        String loadedSource;

        for (SourceProvider providerImplementation : sourceProviders) {
            if (providerImplementation.isAllowed(pathToSource)) {
                provider = providerImplementation;
                break;
            }
        }

        if (provider == null)
            throw new NullPointerException();

        loadedSource = provider.load(pathToSource);
        return loadedSource;
    }
}