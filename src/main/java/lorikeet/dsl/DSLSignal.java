package lorikeet.dsl;

import lorikeet.server.signals.SignalSystemDSL;

public record DSLSignal(SignalSystemDSL system, Object dslSpec) {
}
