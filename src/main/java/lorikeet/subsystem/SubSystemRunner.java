package lorikeet.subsystem;

import lorikeet.dependencies.Dependency;
import lorikeet.server.signals.lifecycle.LifeCycleSignalSystem;

public class SubSystemRunner<KernelType> {

    private final SubSystem<KernelType> subsystem;

    public SubSystemRunner(SubSystem<KernelType> subsystem) {
        this.subsystem = subsystem;
    }

    public void run() {
        this.startDependencies();
        this.startSignalSystems();

        this.signalReady();
    }

    private void signalReady() {
        this.subsystem.signalSystems().forEach((signalSystem) -> {
            if (signalSystem instanceof LifeCycleSignalSystem<KernelType> lifeCycle) {
                lifeCycle.signalReady(this.subsystem.axon());
            }
        });
    }

    private void startDependencies() {
        this.subsystem.dependencies()
            .forEach((dep) -> dep.dependency().init());
    }

    private void startSignalSystems() {
        this.subsystem.signalSystems().forEach(Dependency::init);
    }
}
