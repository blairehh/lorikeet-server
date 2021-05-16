package lorikeet.console;

import lorikeet.dependencies.ConstDependency;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;

public class PrettyConsoleWriter implements ConsoleWriter {
    @Override
    public void dependencyInitStatus(SubSystem<?> subsystem, ConstDependency dependency, InitStatus status) {
        System.out.printf(
            "[%s] %-20s %-20s %s\n",
            colorize(subsystem.name(), BOLD()),
            "dependency",
            dependency.name(),
            textForInitStatus(status)
        );
    }

    @Override
    public void signalSystemInitStatus(SubSystem<?> subsystem, SignalSystem<?> signalSystem, InitStatus status) {
        System.out.printf(
            "[%s] %-20s %-20s %s\n",
            colorize(subsystem.name(), BOLD()),
            "signal-system",
            signalSystem.name(),
            textForInitStatus(status)
        );
    }

    @Override
    public void subsystemReady(SubSystem<?> subsystem) {

    }

    private static String textForInitStatus(InitStatus status) {
        if (status instanceof InitStatus.Ready) {
            return colorize("READY", BOLD(), GREEN_TEXT());
        }
        return colorize("FAILED TO START", BOLD(), RED_TEXT());
    }
}
