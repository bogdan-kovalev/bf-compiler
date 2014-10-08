/**
 * Created by B on 07.10.2014.
 */
public class PrintCellCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.printCurrentCell();
    }
}
