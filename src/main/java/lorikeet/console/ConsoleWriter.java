package lorikeet.console;

import lorikeet.dependencies.Dependency;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

public interface ConsoleWriter {
    void dependencyInitStatus(SubSystem<?> subsystem, Dependency dependency, InitStatus status);
    void signalSystemInitStatus(SubSystem<?> subsystem, SignalSystem<?> signalSystem, InitStatus status);
    void subsystemReady(SubSystem<?> subsystem);
}
