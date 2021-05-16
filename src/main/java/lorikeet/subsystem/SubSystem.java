package lorikeet.subsystem;

import lorikeet.Axon;
import lorikeet.server.signals.SignalSystem;

import java.util.List;

public class SubSystem<KernelType> {
    private final Axon<KernelType> axon;
    private final List<SignalSystem<KernelType>> signalSystems;

    public SubSystem(Axon<KernelType> axon, List<SignalSystem<KernelType>> signalSystems) {
        this.axon = axon;
        this.signalSystems = signalSystems;
    }
}
