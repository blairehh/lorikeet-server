package lorikeet.server;

import lorikeet.Axon;
import lorikeet.console.ConsoleWriter;
import lorikeet.console.PrettyConsoleWriter;
import lorikeet.disk.Disk;
import lorikeet.disk.DiskFile;
import lorikeet.disk.FileDigResult;
import lorikeet.dsl.DSLReader;
import lorikeet.dsl.SubSystemDSLSpec;
import lorikeet.exceptions.SubSystemJARFileNotFoundException;
import lorikeet.server.signals.SignalSystem;
import lorikeet.subsystem.SubSystem;
import lorikeet.subsystem.SubSystemDependency;
import lorikeet.subsystem.SubSystemJAR;
import lorikeet.subsystem.SubSystemJARLoadResult;
import lorikeet.subsystem.SubSystemJARLoader;
import lorikeet.subsystem.SubSystemRunner;

import java.util.List;
import java.util.stream.Collectors;

public class Server {
    private final ServerArgs args;
    private final Disk disk;

    public Server(ServerArgs args) {
        this.args = args;
        this.disk = new Disk();
    }

    public void run() throws Exception {
        final ConsoleWriter console = new PrettyConsoleWriter();
        final FileDigResult result = this.disk.digFile(args.jar());
        if (result instanceof FileDigResult.Found found) {
            final DiskFile file = found.file();
            final SubSystemJARLoader loader = new SubSystemJARLoader();
            final SubSystemJARLoadResult loadResult = loader.load(file);
            if (loadResult instanceof SubSystemJARLoadResult.Ok ok) {
                this.run(file, ok.subsystem(), console);
            } else if (loadResult instanceof SubSystemJARLoadResult.UnexpectedError error) {
                console.error(error.throwable());
            } else if (loadResult instanceof SubSystemJARLoadResult.InvalidJAR invalid) {
                console.problem(invalid.problem());
            }
        } else {
            console.problem(new SubSystemJARFileNotFoundException(args.jar()));
        }

    }

    private void run(DiskFile file, SubSystemJAR subsystemJar, ConsoleWriter console) {
        final DSLReader reader = new DSLReader(file.asJarURL());
        final SubSystemDSLSpec spec = reader.read("index.lorikeet");
        this.run(this.buildSubSystem(spec), console);
    }

    private <KernelType> void run(SubSystem<KernelType> subsystem, ConsoleWriter console) {
        new SubSystemRunner<>(subsystem, console).run();
    }

    @SuppressWarnings("unchecked")
    private <KernelType> SubSystem<KernelType> buildSubSystem(SubSystemDSLSpec spec) {
        final List<SignalSystem<KernelType>> signalSystems = spec.getSignalSystems().stream()
            .map((subsystem) -> (SignalSystem<KernelType>)subsystem.buildSignalSystem())
            .collect(Collectors.toList());

        final List<SubSystemDependency> dependencies = spec.getDependencies()
            .entrySet()
            .stream()
            .map((keyValue) -> new SubSystemDependency(keyValue.getKey(), keyValue.getValue()))
            .collect(Collectors.toList());

        final Axon<KernelType> axon = new DefaultAxon<>((KernelType)spec.getKernel());

        return new SubSystem<>(
            spec.getName(),
            axon,
            dependencies,
            signalSystems
        );
    }
}
