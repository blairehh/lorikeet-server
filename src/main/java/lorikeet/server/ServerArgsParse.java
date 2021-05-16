package lorikeet.server;

public sealed interface ServerArgsParse {
    record OK(ServerArgs args) implements ServerArgsParse {}
    record Error(String error) implements ServerArgsParse {}
}
