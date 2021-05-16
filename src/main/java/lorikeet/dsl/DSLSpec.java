package lorikeet.dsl;

import groovy.lang.Binding;
import groovy.lang.Closure;
import lorikeet.dependencies.Dependency;
import lorikeet.server.signals.SignalSystemDSL;
import lorikeet.server.signals.lifecycle.LifeCycleDSL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DSLSpec {
    private final List<SignalSystemDSL> signals;
    private String name;
    private Object kernel;
    private final Map<String, Dependency> dependencies = new HashMap<>();

    public DSLSpec() {
        this.signals = new ArrayList<>();
    }


    public SignalSystemDSL signals(SignalSystemDSL system, Closure<?> closure) {
        final Object systemDsl = system.dslSpec();
        final Closure<?> code = closure.rehydrate(systemDsl, this, this);
        code.setResolveStrategy(Closure.DELEGATE_ONLY);
        code.call();
        this.signals.add(system);
        return system;
    }

    // ---------------------------------------------------------

    public Binding addSpecBindings(Binding binding) {
        final SignalSystemDSL lifecycle = new LifeCycleDSL();
        binding.setVariable(lifecycle.name(), lifecycle);
        final Closure<?> onSignals = new Closure<Void>(null) {
            public SignalSystemDSL doCall(SignalSystemDSL system, Closure<?> closure) {
                return signals(system, closure);
            }
        };
        final Closure<?> onSubsystem = new Closure<Void>(null) {
            public void doCall(String value) {
                name = value;
            }
        };
        final Closure<?> onKernel = new Closure<Void>(null) {
            public void doCall(Object value) {
                kernel = value;
            }
        };
        final Closure<?> onDependency = new Closure<Void>(null) {
            public Object doCall(Map<String, Dependency> value) {
                final Optional<Map.Entry<String, Dependency>> dep = value.entrySet()
                    .stream()
                    .findFirst();
                if (dep.isEmpty()) {
                    return null;
                }
                dependencies.put(dep.get().getKey(), dep.get().getValue());
                binding.setVariable(dep.get().getKey(), dep.get().getValue());
                return dep.get().getValue();
            }
        };
        binding.setProperty("signals", onSignals);
        binding.setProperty("subsystem", onSubsystem);
        binding.setProperty("kernel", onKernel);
        binding.setProperty("dependency", onDependency);
        return binding;
    }

    // -------------------------------------------------------

    public List<SignalSystemDSL> getSignalSystems() {
        return this.signals;
    }

     public String getName() {
        return this.name;
     }

     public Object getKernel() {
        return this.kernel;
     }

    public Map<String, Dependency> getDependencies() {
        return this.dependencies;
    }
}
