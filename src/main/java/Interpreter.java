import bvm.BrainfuckVirtualMachine;

/**
 * @author Bogdan Kovalev
 */
public interface Interpreter {
    String interpret(String code);

    String interpret(BrainfuckVirtualMachine bvm, String code);
}
