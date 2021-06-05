package lorikeet.console;

import lorikeet.Problem;
import lorikeet.dependencies.ConstDependency;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

public interface ConsoleWriter {
    void dependencyInitStatus(SubSystem<?> subsystem, ConstDependency dependency, InitStatus status);
    void signalSystemInitStatus(SubSystem<?> subsystem, SignalSystem<?> signalSystem, InitStatus status);
    void subsystemReady(SubSystem<?> subsystem);

    void error(Throwable error);
    void problem(Problem problem);
}
