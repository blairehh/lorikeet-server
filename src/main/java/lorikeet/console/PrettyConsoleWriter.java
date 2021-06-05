package lorikeet.console;

import lorikeet.Problem;
import lorikeet.dependencies.ConstDependency;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;
import static com.diogonunes.jcolor.Attribute.DIM;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.ITALIC;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static com.diogonunes.jcolor.Attribute.UNDERLINE;

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


    @Override
    public void error(Throwable error) {
        System.out.printf(
            "[%s] %-20s\n",
            colorize("ERROR", BOLD(), RED_TEXT()),
            error.getMessage()
        );
        System.out.printf(
            "\t    %s: %s\n\t%s: %s (%s)\n\n",
            colorize("error", UNDERLINE(), DIM()),
            colorize(error.getClass().getName(), BOLD()),
            colorize("caused by", UNDERLINE(), DIM()),
            colorize(error.getCause().getClass().getName(), BOLD()),
            colorize(error.getCause().getMessage(), ITALIC(), DIM())
        );
    }

    @Override
    public void problem(Problem problem) {
        System.out.printf(
            "[%s] %-20s\n",
            colorize("PROBLEM", BOLD(), RED_TEXT()),
            colorize(problem.code(), BOLD())
        );

        System.out.printf(
            "\t %s: %s\n\t%s: %s\n\n",
            colorize("about", UNDERLINE(), DIM()),
            problem.about(),
            colorize("remedy", UNDERLINE(), DIM()),
            problem.remedy()
        );
    }

    private static String textForInitStatus(InitStatus status) {
        if (status instanceof InitStatus.Ready) {
            return colorize("READY", BOLD(), GREEN_TEXT());
        }
        return colorize("FAILED TO START", BOLD(), RED_TEXT());
    }
}
