package lorikeet.subsystem;

import lorikeet.dependencies.Dependency;

public class SubSystemRunner<KernelType> {

    private final SubSystem<KernelType> subsystem;

    public SubSystemRunner(SubSystem<KernelType> subsystem) {
        this.subsystem = subsystem;
    }

    public void run() {
        this.startDependencies();
        this.startSignalSystems();
        // ((LifeCycleDSLSpec)spec.getSignals().get(0).dslSpec()).getReady().onReady(axon);
    }

    private void startDependencies() {
        this.subsystem.dependencies()
            .forEach((dep) -> dep.dependency().init());
    }

    private void startSignalSystems() {
        this.subsystem.signalSystems().forEach(Dependency::init);
    }
}
