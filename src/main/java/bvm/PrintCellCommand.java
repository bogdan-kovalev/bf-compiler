package bvm;

/**
 * @author Bogdan Kovalev
 */
public class PrintCellCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.printCurrentCell();
    }
}
