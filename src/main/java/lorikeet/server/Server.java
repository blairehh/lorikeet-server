package lorikeet.server;

import lorikeet.Axon;
import lorikeet.DefaultAxon;
import lorikeet.dependencies.Dependency;
import lorikeet.disk.Disk;
import lorikeet.disk.DiskFile;
import lorikeet.disk.FileDigResult;
import lorikeet.dsl.DSLReader;
import lorikeet.dsl.DSLSpec;
import lorikeet.subsystem.SubSystemJAR;
import lorikeet.subsystem.SubSystemJARLoader;

import java.util.Map;

public class Server {
    private final ServerArgs args;
    private final Disk disk;

    public Server(ServerArgs args) {
        this.args = args;
        this.disk = new Disk();
    }

    public void run() throws Exception {

        final FileDigResult result = this.disk.digFile(args.jar());
        if (result instanceof FileDigResult.Found found) {
            final DiskFile file = found.file();

            final SubSystemJARLoader loader = new SubSystemJARLoader();
            final SubSystemJAR subsystemJar = loader.load(file);

            final DSLReader reader = new DSLReader(file.asJarURL());
            final DSLSpec spec = reader.read("index.lorikeet");


            System.out.println("found subsystem " + spec.getName());
            System.out.println("kernel is " + spec.getKernel().getClass().getName());

            this.run(this.buildAxon(spec), spec);
        } else {
            System.out.println("could not find " + args.jar());
        }

    }

    private <T> void run(Axon<T> axon, DSLSpec spec) {
        spec.getDependencies()
            .entrySet()
            .forEach(this::initDependency);

        // ((LifeCycleDSLSpec)spec.getSignals().get(0).dslSpec()).getReady().onReady(axon);
    }

    private void initDependency(Map.Entry<String, Dependency> keyValue) {
        final String name = keyValue.getKey();
        final Dependency dependency = keyValue.getValue();

        dependency.init();
    }

    @SuppressWarnings("unchecked")
    private <T> Axon<T> buildAxon(DSLSpec spec) {
        return new DefaultAxon<>((T)spec.getKernel());
    }
}
