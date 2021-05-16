package lorikeet.subsystem;

import lorikeet.server.signals.SignalSystem;

import java.util.List;

public class SubSystem {
    private final List<SignalSystem> signalSystems;

    public SubSystem(List<SignalSystem> signalSystems) {
        this.signalSystems = signalSystems;
    }
}
