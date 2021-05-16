package lorikeet.server.signals.lifecycle;

import lorikeet.server.signals.SignalSystem;
import lorikeet.server.signals.SignalSystemDSL;
import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

import java.util.List;
import java.util.stream.Collectors;

public class LifeCycleDSL implements SignalSystemDSL {

    private final LifeCycleDSLSpec dslSpec;

    public LifeCycleDSL() {
        this.dslSpec = new LifeCycleDSLSpec();
    }

    @Override
    public String name() {
        return "lifecycle";
    }

    @Override
    public Object dslSpec() {
        return this.dslSpec;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <KernelType> SignalSystem<KernelType> buildSignalSystem() {
        final List<SubSystemReadyReceptor<KernelType>> readyReceptors = this.dslSpec.getReady()
            .stream()
            .map((ready) -> (SubSystemReadyReceptor<KernelType>)ready)
            .collect(Collectors.toList());

        return new LifeCycleSignalSystem<>(readyReceptors);
    }
}
