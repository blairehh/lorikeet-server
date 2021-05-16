package lorikeet.console;

import lorikeet.dependencies.Dependency;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

public class SimpleConsoleWriter implements ConsoleWriter {
    @Override
    public void dependencyInitStatus(SubSystem<?> subsystem, Dependency dependency, InitStatus status) {
        System.out.println("dependency " + dependency.name() + " " + status.getClass().getSimpleName());
    }

    @Override
    public void signalSystemInitStatus(SubSystem<?> subsystem, SignalSystem<?> signalSystem, InitStatus status) {
        System.out.println(signalSystem.name() + " " + status.getClass().getSimpleName());
    }

    @Override
    public void subsystemReady(SubSystem<?> subsystem) {
        System.out.println(subsystem.name() + " is ready!");
    }
}
