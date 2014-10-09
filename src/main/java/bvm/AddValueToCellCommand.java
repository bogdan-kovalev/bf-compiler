package bvm;

/**
 * @author Bogdan Kovalev
 */
public class AddValueToCellCommand implements Command {
    private final int value;

    public AddValueToCellCommand(int value) {
        this.value = value;
    }

    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.setCurrentMemoryCell((char) (bvm.getCurrentCell() + value));
    }
}
