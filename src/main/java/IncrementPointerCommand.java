/**
 * Created by B on 07.10.2014.
 */
public class IncrementPointerCommand implements Command {
    @Override
    public void execute(BrainfuckVirtualMachine bvm) {
        bvm.incrementPointer();
    }
}
