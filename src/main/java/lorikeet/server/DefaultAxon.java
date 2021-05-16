package lorikeet.server;

import lorikeet.Axon;
import lorikeet.Cell;

public class DefaultAxon<KernelType> implements Axon<KernelType> {

    private final KernelType kernel;

    public DefaultAxon(KernelType kernel) {
        this.kernel = kernel;
    }

    @Override
    public <ReturnType> ReturnType run(Cell<ReturnType, KernelType> cell) {
        return cell.junction()
            .invoke(this, this.kernel);
    }
}
