package bvm;

/**
 * @author Bogdan Kovalev
 */
public class IncrementCellCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.incrementCurrentCell();
    }
}
