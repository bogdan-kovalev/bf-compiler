package bvm;

/**
 * @author Bogdan Kovalev
 */
public class DecrementPointerCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.decrementPointer();
    }
}
