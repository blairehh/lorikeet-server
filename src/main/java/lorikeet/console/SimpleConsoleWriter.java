package lorikeet.console;

import lorikeet.dependencies.Dependency;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

public class SimpleConsoleWriter implements ConsoleWriter {
    @Override
    public void dependencyStarted(SubSystem<?> subsystem, Dependency dependency) {
        System.out.println("started " + dependency.name());
    }

    @Override
    public void signalSystemStarted(SubSystem<?> subsystem, SignalSystem<?> signalSystem) {
        System.out.println("started " + signalSystem.name());
    }

    @Override
    public void subsystemReady(SubSystem<?> subsystem) {
        System.out.println(subsystem.name() + " is ready!");
    }
}
