package lorikeet.server.signals.lifecycle;

import lorikeet.server.signals.SignalSystem;
import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

import java.util.List;

public class LifeCycleSignalSystem<KernelType> implements SignalSystem<KernelType> {

    private final List<SubSystemReadyReceptor<KernelType>> readyReceptors;

    public LifeCycleSignalSystem(List<SubSystemReadyReceptor<KernelType>> readyReceptors) {
        this.readyReceptors = readyReceptors;
    }

    @Override
    public String name() {
        return "lifecycle";
    }
}
