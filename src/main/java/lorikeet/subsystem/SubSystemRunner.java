package lorikeet.subsystem;

import lorikeet.console.ConsoleWriter;
import lorikeet.dependencies.InitStatus;
import lorikeet.server.signals.lifecycle.LifeCycleSignalSystem;

public class SubSystemRunner<KernelType> {

    private final SubSystem<KernelType> subsystem;
    private final ConsoleWriter console;

    public SubSystemRunner(SubSystem<KernelType> subsystem, ConsoleWriter console) {
        this.subsystem = subsystem;
        this.console = console;
    }

    public void run() {
        this.startDependencies();
        this.startSignalSystems();

        this.signalReady();
    }

    private void signalReady() {
        this.console.subsystemReady(this.subsystem);
        this.subsystem.signalSystems().forEach((signalSystem) -> {
            if (signalSystem instanceof LifeCycleSignalSystem<KernelType> lifeCycle) {
                lifeCycle.signalReady(this.subsystem.axon());
            }
        });
    }

    private void startDependencies() {
        this.subsystem.dependencies().forEach((dep) -> {
            final InitStatus status = dep.dependency().init();
            this.console.dependencyInitStatus(this.subsystem, dep.dependency(), status);
        });
    }

    private void startSignalSystems() {
        this.subsystem.signalSystems().forEach((signalSystem) -> {
            final InitStatus status = signalSystem.init();
            this.console.signalSystemInitStatus(this.subsystem, signalSystem, status);
        });
    }
}
