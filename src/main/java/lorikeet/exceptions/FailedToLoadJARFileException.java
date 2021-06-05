package lorikeet.exceptions;

public class FailedToLoadJARFileException extends RuntimeException {

    private final String file;

    public FailedToLoadJARFileException(String file, Throwable cause) {
        super(cause);
        this.file = file;
    }

    @Override
    public String getMessage() {
        return String.format("Could not load subsystem jar from file '%s'", this.file);
    }
}
