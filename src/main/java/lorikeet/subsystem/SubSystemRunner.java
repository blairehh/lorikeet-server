package lorikeet.subsystem;

import lorikeet.console.ConsoleWriter;
import lorikeet.dependencies.Dependency;
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
            dep.dependency().init();
            this.console.dependencyStarted(this.subsystem, dep.dependency());
        });
    }

    private void startSignalSystems() {
        this.subsystem.signalSystems().forEach((signalSystem) -> {
            signalSystem.init();
            this.console.signalSystemStarted(this.subsystem, signalSystem);
        });
    }
}
