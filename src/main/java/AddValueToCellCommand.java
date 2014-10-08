/**
 * Created by B on 07.10.2014.
 */
public class AddValueToCellCommand implements Command {
    private int value;

    public AddValueToCellCommand(int value) {
        this.value = value;
    }

    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.setCurrentMemoryCell((char) (bvm.getCurrentCell()+value));
    }
}
