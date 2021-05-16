package lorikeet.server.signals.lifecycle;

import lorikeet.server.signals.SignalSystemDSL;

public class LifeCycleDSL implements SignalSystemDSL {
    @Override
    public String name() {
        return "lifecycle";
    }

    @Override
    public Object dslSpec() {
        return new LifeCycleDSLSpec();
    }
}
