package task2.source;

import task2.extensions.Audio;
import task2.extensions.Image;
import task2.extensions.Video;

import java.util.ArrayList;
import java.util.List;

public class FileFiltersManager {
    private List<ExtensionsFileFilter> fileFilters = new ArrayList<>();

    public FileFiltersManager() {
        fileFilters.add(new ExtensionsFileFilter(Audio.values(), "Audio files filter"));
        fileFilters.add(new ExtensionsFileFilter(Image.values(), "Image files filter"));
        fileFilters.add(new ExtensionsFileFilter(Video.values(), "Video files filter"));
    }

    public ExtensionsFileFilter getAudioFileFilter() {
        return fileFilters.get(0);
    }

    public ExtensionsFileFilter getImageFileFilter() {
        return fileFilters.get(1);
    }

    public ExtensionsFileFilter getVideoFileFilter() {
        return fileFilters.get(2);
    }
}
