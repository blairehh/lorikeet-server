package lorikeet.console;

import lorikeet.dependencies.Dependency;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

public interface ConsoleWriter {
    void dependencyStarted(SubSystem<?> subsystem, Dependency dependency);
    void signalSystemStarted(SubSystem<?> subsystem, SignalSystem<?> signalSystem);
    void subsystemReady(SubSystem<?> subsystem);
}
