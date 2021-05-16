package lorikeet.server.signals.lifecycle;

import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

import java.util.ArrayList;
import java.util.List;

public class LifeCycleDSLSpec {
    private final List<SubSystemReadyReceptor<?>> ready;

    public LifeCycleDSLSpec() {
        this.ready = new ArrayList<>();
    }

    public void ready(SubSystemReadyReceptor<?> value) {
        this.ready.add(value);
    }

    public List<SubSystemReadyReceptor<?>> getReady() {
        return this.ready;
    }
}
