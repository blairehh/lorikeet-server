package lorikeet.server.signals.lifecycle;

import lorikeet.server.signals.SignalSystem;
import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

import java.util.List;

public class LifecycleSignalSystem<KernelType> implements SignalSystem {

    private final List<SubSystemReadyReceptor<KernelType>> readyReceptors;

    public LifecycleSignalSystem(List<SubSystemReadyReceptor<KernelType>> readyReceptors) {
        this.readyReceptors = readyReceptors;
    }

    @Override
    public String name() {
        return "lifecycle";
    }
}
