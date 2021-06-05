package lorikeet.exceptions;

import lorikeet.Problem;

public class SubSystemJARFileNotFoundException extends RuntimeException implements Problem {
    private final String jarFile;

    public SubSystemJARFileNotFoundException(String jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public String code() {
        return "SUBSYSTEM_JAR_FILE_NOT_FOUND";
    }

    @Override
    public String about() {
        return String.format("Could not find subsystem JAR file '%s'", this.jarFile);
    }

    @Override
    public String remedy() {
        return "Provide the correct path to the subsystem JAR file";
    }
}
