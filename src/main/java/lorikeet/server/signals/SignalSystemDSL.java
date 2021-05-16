package lorikeet.server.signals;

public interface SignalSystemDSL {
    String name();

    Object dslSpec();

    <KernelType> SignalSystem<KernelType> buildSignalSystem();
}
