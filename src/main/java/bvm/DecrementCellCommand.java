package bvm;

/**
 * @author Bogdan Kovalev
 */
public class DecrementCellCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.decrementCurrentCell();
    }
}
