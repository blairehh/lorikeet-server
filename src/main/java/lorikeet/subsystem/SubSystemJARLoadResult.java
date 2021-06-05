package lorikeet.subsystem;

import lorikeet.Problem;

public sealed interface SubSystemJARLoadResult {
    record Ok(SubSystemJAR subsystem) implements SubSystemJARLoadResult {}
    record InvalidJAR(Problem problem) implements SubSystemJARLoadResult {}
    record UnexpectedError(Throwable throwable) implements SubSystemJARLoadResult {}
}
