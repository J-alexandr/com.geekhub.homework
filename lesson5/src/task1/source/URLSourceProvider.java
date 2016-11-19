package task1.source;

import task1.exceptions.SourceLoadingException;

import java.io.IOException;
import java.net.URL;

/**
 * Implementation for loading content from specified URL.<br/>
 * Valid paths to load are http://someurl.com, https://secureurl.com, ftp://frpurl.com etc.
 */
public class URLSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) {
        try {
            new URL(pathToSource);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String load(String pathToSource) throws SourceLoadingException {
        try {
            URL url = new URL(pathToSource);
            return SourceUtils.toString(url.openStream());
        } catch (IOException e) {
            throw new SourceLoadingException(e);
        }
    }
}