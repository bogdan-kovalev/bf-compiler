package bvm;

/**
 * Created by B on 07.10.2014.
 */
public class DecrementCellCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.decrementCurrentCell();
    }
}
