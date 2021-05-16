package lorikeet.subsystem;

import lorikeet.Axon;
import lorikeet.server.signals.SignalSystem;

import java.util.List;

public class SubSystem<KernelType> {
    private final String name;
    private final Axon<KernelType> axon;
    private final List<SubSystemDependency> dependencies;
    private final List<SignalSystem<KernelType>> signalSystems;

    public SubSystem(
        String name,
        Axon<KernelType> axon,
        List<SubSystemDependency> dependencies,
        List<SignalSystem<KernelType>> signalSystems
    ) {
        this.name = name;
        this.axon = axon;
        this.dependencies = dependencies;
        this.signalSystems = signalSystems;
    }

    public String name() {
        return this.name;
    }

    public List<SubSystemDependency> dependencies() {
        return this.dependencies;
    }

    public List<SignalSystem<KernelType>> signalSystems() {
        return this.signalSystems;
    }

    public Axon<KernelType> axon() {
        return this.axon;
    }
}
