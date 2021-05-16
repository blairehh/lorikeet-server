package lorikeet.server.signals.lifecycle;

import lorikeet.Axon;
import lorikeet.dependencies.CheckStatus;
import lorikeet.dependencies.InitStatus;
import lorikeet.dependencies.RetireStatus;
import lorikeet.server.signals.SignalSystem;
import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

import java.util.List;

public class LifeCycleSignalSystem<KernelType> implements SignalSystem<KernelType> {

    private final List<SubSystemReadyReceptor<KernelType>> readyReceptors;

    public LifeCycleSignalSystem(List<SubSystemReadyReceptor<KernelType>> readyReceptors) {
        this.readyReceptors = readyReceptors;
    }

    public void signalReady(Axon<KernelType> axon) {
        this.readyReceptors.forEach((ready) -> ready.onReady(axon));
    }

    @Override
    public String name() {
        return "lifecycle";
    }

    @Override
    public InitStatus init() {
        return new InitStatus.Ready();
    }

    @Override
    public RetireStatus retire() {
        return new RetireStatus.Stopped();
    }

    @Override
    public CheckStatus check() {
        return new CheckStatus.Healthy();
    }

    @Override
    public long version() {
        return 1;
    }
}
