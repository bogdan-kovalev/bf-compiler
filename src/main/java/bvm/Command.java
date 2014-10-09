package bvm;

/**
 * @author Bogdan Kovalev
 */
public interface Command {
    public void execute(BrainfuckVirtualMachine bvm);
}
