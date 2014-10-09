package bvm;

/**
 * @author Bogdan Kovalev
 */
public class IncrementPointerCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.incrementPointer();
    }
}
