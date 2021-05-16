package lorikeet.server.signals.lifecycle;

import lorikeet.signals.lifecycle.SubSystemReadyReceptor;

public class LifeCycleDSLSpec {
    private SubSystemReadyReceptor<?> ready;

    public void ready(SubSystemReadyReceptor<?> value) {
        this.ready = value;
    }

    public SubSystemReadyReceptor<?> getReady() {
        return this.ready;
    }
}
