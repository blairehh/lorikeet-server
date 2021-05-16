package lorikeet.disk;

import lorikeet.exceptions.URLInvalidForDiskFileBugException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public record DiskFile(String fullPath, File file) {
    public URL asJarURL() {
        try {
            return new URL("jar:file:" + fullPath +"!/");
        } catch (MalformedURLException e) {
            throw new URLInvalidForDiskFileBugException(e);
        }
    }
}
