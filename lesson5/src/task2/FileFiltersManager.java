package task2;

import task2.extensions.Audio;
import task2.extensions.Image;
import task2.extensions.Video;

import java.util.ArrayList;
import java.util.List;

class FileFiltersManager {
    private List<ExtensionsFileFilter> fileFilters = new ArrayList<>();

    FileFiltersManager() {
        fileFilters.add(new ExtensionsFileFilter(Audio.values(), "Audio files filter"));
        fileFilters.add(new ExtensionsFileFilter(Image.values(), "Image files filter"));
        fileFilters.add(new ExtensionsFileFilter(Video.values(), "Video files filter"));
    }

    ExtensionsFileFilter getAudioFileFilter() {
        return fileFilters.get(0);
    }

    ExtensionsFileFilter getImageFileFilter() {
        return fileFilters.get(1);
    }

    ExtensionsFileFilter getVideoFileFilter() {
        return fileFilters.get(2);
    }
}
