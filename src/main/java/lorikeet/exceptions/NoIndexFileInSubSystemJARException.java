package lorikeet.exceptions;

import lorikeet.Problem;
import lorikeet.disk.DiskFile;

public class NoIndexFileInSubSystemJARException extends RuntimeException implements Problem {
    private final String jarFile;

    public NoIndexFileInSubSystemJARException(DiskFile file) {
        this.jarFile = file.fullPath();
    }

    public NoIndexFileInSubSystemJARException(String jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public String code() {
        return "NO_INDEX_FILE";
    }

    @Override
    public String about() {
        return String.format(
            "The 'index.lorikeet' file was not found in the subsystem JAR file '%s'",
            this.jarFile
        );
    }

    @Override
    public String remedy() {
        return "Create a file 'index.lorikeet' at the root of the JAR file";
    }
}
