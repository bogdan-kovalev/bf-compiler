/**
 * Created by B on 07.10.2014.
 */
public class DecrementPointerCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.decrementPointer();
    }
}
